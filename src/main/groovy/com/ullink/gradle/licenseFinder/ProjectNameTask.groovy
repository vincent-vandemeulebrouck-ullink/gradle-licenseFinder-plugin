package com.ullink.gradle.licenseFinder

import com.github.jrubygradle.JRubyExec
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class ProjectNameTask extends DefaultTask {
    private File scriptEntrypoint
    private ArrayList<String> scriptArgs

    @Inject
    ProjectNameTask(Project project) {
        scriptEntrypoint = new File(this.class.getResource('/entrypoint.rb').toURI())
        scriptArgs = ["project_anme", "add"]
    }

    @TaskAction
    exec() {
        def ex = new JRubyExec(script: this.scriptEntrypoint, scriptArgs: scriptArgs)
        ex.exec()
    }
}
