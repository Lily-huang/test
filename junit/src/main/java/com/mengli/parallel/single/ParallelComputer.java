package com.mengli.parallel.single;

import org.junit.runner.Computer;
import org.junit.runner.Runner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mlhuang on 7/8/16.
 */
public class ParallelComputer extends Computer {
    private final boolean classes;
    private final boolean methods;

    public ParallelComputer(boolean classes, boolean methods) {
        this.classes = classes;
        this.methods = methods;
    }

    public static Computer classes() {
        return new ParallelComputer(true, false);
    }

    public static Computer methods() {
        return new ParallelComputer(false, true);
    }

    private static Runner parallelize(Runner runner) {
        if (runner instanceof ParentRunner) {
            ((ParentRunner<?>) runner).setScheduler(new RunnerScheduler() {
                private final ExecutorService fService = Executors.newCachedThreadPool();

                @Override
                public void schedule(Runnable childStatement) {
                    fService.submit(childStatement);
                }

                @Override
                public void finished() {
                    fService.shutdown();
                    try {
                        fService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return runner;
    }

    @Override
    public Runner getSuite(RunnerBuilder builder, java.lang.Class<?>[] classes) throws InitializationError {
        Runner suite = super.getSuite(builder, classes);
        return this.classes ? parallelize(suite) : suite;
    }

    @Override
    protected Runner getRunner(RunnerBuilder builder, Class<?> testClass) throws Throwable {
        Runner runner = super.getRunner(builder, testClass);
        return methods ? parallelize(runner) : runner;
    }
}
