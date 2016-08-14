package com.mengli.mockapp.runner

import org.jbehave.core.embedder.Embedder
import org.jbehave.core.failures.FailingUponPendingStep
import org.jbehave.core.io.CodeLocations
import org.jbehave.core.io.LoadFromClasspath
import org.jbehave.core.junit.JUnitStories
import org.jbehave.core.model.ExamplesTableFactory
import org.jbehave.core.parsers.RegexStoryParser
import org.jbehave.core.parsers.StoryParser
import org.jbehave.core.steps.ParameterControls

/**
 * Created by mlhuang on 8/10/16.
 */


abstract class AbstractStoriesRunner extends JUnitStories {
    public static final String META_FILTERS = 'meta.filters'
    private static final int FEATURE_TIMEOUT_IN_HOURS = 8

    @Override
    Embedder configuredEmbedder() {
        Embedder embedder = super.configuredEmbedder()

        embedder.configuration().storyReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromPath('build/reports'))
                .withRelativeDirectory('reports/jbehave')
                .withFormats(STATS, HTML, CONSOLE)
                .withFailureTrace(true)

        embedder.useMetaFilters parseMetaFilters(System.getProperty(META_FILTERS, ''))
        embedder.configuration()
                .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
                .usePendingStepStrategy(new FailingUponPendingStep())

        embedder.embedderControls().useStoryTimeoutInSecs(TimeUit.HOURS.toSeconds(FEATURE_TIMEOUT_IN_HOURS))

        embedder
    }

    private List<String> parseMetaFilter(String text) {
        text.split(';')*.trim().findAll { String filter ->
            filter.size() > 0
        }
    }

    private StoryParser retriveStoryParser() {
        new RegexStoryParser(new ExamplesTableFactory(new LoadFromClasspath(this.getClass())))
    }

}
