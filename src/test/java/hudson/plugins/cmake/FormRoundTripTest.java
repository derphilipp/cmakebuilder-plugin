package hudson.plugins.cmake;

import hudson.model.FreeStyleProject;

import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class FormRoundTripTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void checkValidation() throws Exception {

        FreeStyleProject p = j.createFreeStyleProject();
        CmakeBuilder before = new CmakeBuilder(CmakeTool.DEFAULT,
                "Unix Makefiles");
        p.getBuildersList().add(before);

        j.submit(j.createWebClient().getPage(p, "configure")
                .getFormByName("config"));

        CmakeBuilder after = p.getBuildersList().get(CmakeBuilder.class);

        j.assertEqualBeans(
                before,
                after,
                "installationName,generator,sourceDir,buildDir,buildType,cleanBuild,preloadScript,cmakeArgs");

    }

}
