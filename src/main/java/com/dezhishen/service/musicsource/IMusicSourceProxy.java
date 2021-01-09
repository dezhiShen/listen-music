package com.dezhishen.service.musicsource;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.dezhishen.constant.CacheKey.MUSIC_URL;
import static com.dezhishen.constant.CacheKey.SONG;

public interface IMusicSourceProxy {


    /**
     * 根据id获取音乐信息
     *
     * @param source
     * @param id
     * @return
     */
    Song getSongById(String source, String id);

    /**
     * 根据id source 获取音乐播放地址
     *
     * @param source
     * @param id
     * @return
     */
    String getSongUrlById(String source, String id);

    /**
     * 搜索歌曲
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize);

    /**
     * 搜索用户
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize);

    /**
     * 搜索播放列表
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize);
}