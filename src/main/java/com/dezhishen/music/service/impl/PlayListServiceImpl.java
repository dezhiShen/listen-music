package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.PlayListSong;
import com.dezhishen.music.domain.Song;
import com.dezhishen.music.mapper.PlayListMapper;
import com.dezhishen.music.mapper.PlayListSongMapper;
import com.dezhishen.music.service.MusicService;
import com.dezhishen.music.service.PlayListService;
import com.dezhishen.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class PlayListServiceImpl extends AbstractServiceImpl<PlayList> implements PlayListService {

    @Autowired
    private PlayListMapper mapper;
    @Autowired
    private PlayListSongMapper playListSongMapper;
    @Autowired
    private MusicService musicService;
    @Autowired
    private SongService songService;

    @Override
    protected BaseMapper<PlayList> mapper() {
        return mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Song addSong(String playListId, Song song) {
        PlayListSong record = new PlayListSong();
        record.setPlayListId(playListId);
        record.setSource(song.getSource());
        record.setSongId(song.getId());
        if (playListSongMapper.selectCount(new QueryWrapper<>(record)) > 0) {
            return null;
        }
        playListSongMapper.insert(record);
        Song result = musicService.getSongBySourceAndId(record.getSource(), record.getSongId());
        songService.insertAsync(result);
        return result;
    }

    @Override
    public boolean removeSong(String playListId, String source, String songId) {
        PlayListSong record = new PlayListSong();
        record.setSongId(songId);
        record.setSource(source);
        record.setPlayListId(playListId);
        playListSongMapper.delete(new QueryWrapper<>(record));
        return true;
    }

    @Override
    public List<Song> selectSongs(String playListId) {
        return playListSongMapper.selectSongByPlayListId(playListId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        PlayListSong record = new PlayListSong();
        record.setPlayListId(id);
        playListSongMapper.delete(new UpdateWrapper<>(record));
        return super.delete(id);
    }
}