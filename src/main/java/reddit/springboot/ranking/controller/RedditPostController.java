package reddit.springboot.ranking.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reddit.springboot.ranking.crawler.RedditCrawler;
import reddit.springboot.ranking.indexing.Indexer;
import reddit.springboot.ranking.models.RedditPost;
import reddit.springboot.ranking.ranking.Ranker;

@RestController
@RequestMapping(value="/reddit")
public class RedditPostController {
    
    public RedditPostController() {
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ArrayList<RedditPost> searchReddit(@RequestParam(value="search", required=false, defaultValue="Politics") String searchString) {
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        new Indexer("./index").search(searchString);
        return posts;
    }
    
    @RequestMapping(value="/latestsubreddits", method=RequestMethod.GET)
    public ArrayList<RedditPost> getLatestPosts(@RequestParam(value="subreddit", required=false, defaultValue="Politics") String subreddit) {
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        return posts;
    }
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public ArrayList<RedditPost> testReddit() {
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        return posts;
    }
    
    @RequestMapping(value="/subreddits", method=RequestMethod.GET)
    public ArrayList<RedditPost> getSubReddits(@RequestParam(value="name", required=false, defaultValue="Politics") String subredditName) {
        ArrayList<RedditPost> posts = new RedditCrawler().crawlReddits(subredditName);
        return new Ranker().getRankedRedditPost(posts);
    }
    
    public static void main(String[] args){
        new Indexer("./index").search("Happy Father");
    }
    
}
