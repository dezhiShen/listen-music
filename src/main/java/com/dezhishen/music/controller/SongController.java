package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.MusicUser;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.Song;
import com.dezhishen.music.service.MusicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 音乐
 *
 * @author dezhishen
 */
@RestController
@RequestMapping("/song")
public class SongController extends BaseController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/search")
    public RespEntity<PageInfo<Song>> search(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchSong(q, source, pageNum, pageSize));
    }

    @GetMapping("/getBySourceAndId")
    public RespEntity<Song> getBySourceAndId(@RequestParam String source, @RequestParam String id) {
        return success(musicService.getSongBySourceAndId(source, id));
    }

    @GetMapping("/getUrlBySourceAndId")
    public RespEntity<String> getUrlBySourceAndId(@RequestParam String source, @RequestParam String id) {
        return success(musicService.getSongUrlBySourceAndId(source, id));
    }

    @GetMapping("/searchMusicUser")
    public RespEntity<PageInfo<MusicUser>> searchMusicUser(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchMusicUser(q, source, pageNum, pageSize));
    }

    @PostMapping("/searchPlayList")
    public RespEntity<PageInfo<PlayList>> searchPlayList(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchPlayList(q, source, pageNum, pageSize));
    }


    @GetMapping("/updateCover")
    public RespEntity<Boolean> update() {
        return success(musicService.updateCover());
    }
}
