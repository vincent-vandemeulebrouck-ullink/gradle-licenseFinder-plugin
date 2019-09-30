package com.ullink.gradle.licenseFinder

import com.ullink.gradle.entity.DependenciesDecisionEntity
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class LicenseFinderPluginSpec extends Specification {
    def 'simple test'() {
        given:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'licenseFinder'

        when:
        project.evaluate()

        then:
        project.tasks.downloadLicenses
        project.tasks.downloadLicenses.dependencyConfiguration == "runtime"
    }

    def 'added decision '() {
        given:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'licenseFinder'
        project.extensions.licenseFinder.decisions.add(new DependenciesDecisionEntity("gradle-ullink-plugin", "1.42", "ITIVITI", "", true))

        when:
        project.evaluate()

        then:
        project.extensions.licenseFinder.decisions.size() == 1
        project.tasks.makeDecisionFile
    }

    def 'project name in report'() {
        given:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'licenseFinder'

        when:
        project.evaluate()

        then:
        project.tasks.addProjectName
    }

}
