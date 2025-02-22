package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import com.ullink.gradle.entity.DependenciesDecisionEntity
import com.ullink.gradle.utils.StreamUtil
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject


class MakeDecisionTask extends DefaultTask {
    private ArrayList<DependenciesDecisionEntity> decisions
    private File entrypointFile
    private ArrayList<String> scriptArgs

    @Inject
    MakeDecisionTask(Project project){
        this.decisions = project.extensions.licenseFinder.decisions
        this.entrypointFile = project.extensions.licenseFinder.checkLicensesScript
        this.scriptArgs = ["dependencies", "add"]
    }

    @TaskAction
    exec(){
        decisions.forEach { descision ->
            def args = this.scriptArgs.join(descision.projectName, decisions.shortLicenseName, decisions.version, (decisions.homepage) ? "--homepage=${descision.homepage}" : "", (descision.approove) ? "--approove" : "")
            def ex = new JRubyExec(script: this.entrypointFile, scriptArgs: args)
            ex.exec()
        }
    }
}
