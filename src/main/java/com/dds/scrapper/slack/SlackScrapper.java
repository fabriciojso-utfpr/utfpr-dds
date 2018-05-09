
package com.dds.scrapper.slack;

import com.dds.model.Collaborator;
import com.dds.model.Project;
import com.dds.model.Site;
import com.dds.model.Slack;
import java.util.HashMap;


public class SlackScrapper {
    
    protected Project project;
    
    public SlackScrapper(Project project){
        this.project = project;
    }
    
    public HashMap<String, Canal> build(){
        this.project.getSites().forEach((Site site) -> {
             site.getCollaborators().forEach((Collaborator collaborator) -> {
               collaborator.getTools().stream().filter((t) -> {
                     return (t instanceof Slack);
                 }).forEach((item) -> {
                    Slack slack = (Slack) item;
                    ChannelScrapper cs = new ChannelScrapper(slack.getToken());
                 });
             });
        });
        
        return ChannelScrapper.getChannels();
    }
    
}
