package com.mengli.mockapp.runner

import groovy.transform.TypeChecked
import org.apache.commons.lang.StringUtils
import org.jbehave.core.io.StoryFinder
import org.jbehave.core.steps.InjectableStepsFactory
import org.jbehave.core.steps.spring.SpringStepsFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.util.Assert

/**
 * Created by mlhuang on 8/10/16.
 */

@TypeChecked
class AdhocStoryRunner extends AbstractStoriesRunner{
    static final String DEFAULT_STORY_FOLDER='stories/'
    static final String FEATURE_NAME='feature.name'

    @Override
    InjectableStepsFactory stepsFactory(){
        new SpringStepsFactory(configuration(),applicationContext)
    }

    @Override
    protected List<String> storyPaths(){
        List<String> storyPaths=new StoryFinder().findPaths(
                storyClassFolderAsURL,
                storiesToInclude,
                storiesToExclude
        )
        assert  storyPaths.size() > 0:"No stories have been found for: $storiesToInclude"
        storyPaths
    }

    List<String> getStoriesToExclude() {
        ['**/spending*.story']
    }

    List<String> getStoriesToInclude() {
        ["${DEFAULT_STORY_FOLDER}${System.getProperty('story.mask','**/*.story')}"]*.toString()
    }

    String getStoryClassFolderAsURL() {
        URL location=getClass().classLoader.getResource(DEFAULT_STORY_FOLDER)
        String classFolder=StringUtils.removeEnd(location.path,DEFAULT_STORY_FOLDER)
        location =new File(classFolder).toURI().toURL()
        location.file
    }

    ApplicationContext getApplicationContext(){
        def packageName= featureName.toLowerCase()
        new AnnotationConfigApplicationContext("$packageName")
    }

    String getFeatureName(){
        def featureName=System.getProperty(FEATURE_NAME)
        Assert.hasLength(featureName,"System property 'feature.name' cannot be empty")
        featureName
    }
}
