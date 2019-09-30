package com.ullink.gradle.licenseFinder

import com.github.jrubygradle.JRubyExec
import org.gradle.api.Plugin
import org.gradle.api.Project
import nl.javadude.gradle.plugins.license.LicensePlugin
import com.github.jrubygradle.JRubyPlugin

class LicenseFinderPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('licenseFinder', LicenseFinderExtension, project)
        project.apply plugin: LicensePlugin
        project.apply plugin: JRubyPlugin

        if(!project.tasks.downloadLicenses)
            project.tasks.create("downloadLicenses", {dependencyConfiguration extension.dependencyConfiguration})
        else
            project.tasks.downloadLicenses.dependencyConfiguration extension.dependencyConfiguration

        def checkLicensesTask  =  project.tasks.create('checkLicenses', JRubyExec)
        checkLicensesTask.description extension.checkLicensesDescription
        checkLicensesTask.script extension.checkLicensesScript
        checkLicensesTask.scriptArgs extension.checkLicensesScriptArgs

        def addProjectNameTask = project.tasks.create("addProjectName", ProjectNameTask, project)
        def makeDecisionFileTask = project.tasks.create("makeDecisionFile", MakeDecisionTask, project)
        def makeWhtieListTask = project.tasks.create("makeWhiteList", WhiteListTask, project)

        makeDecisionFileTask.dependsOn(makeWhtieListTask)
        makeDecisionFileTask.dependsOn(addProjectNameTask)
        checkLicensesTask.dependsOn(makeDecisionFileTask)
    }
}
