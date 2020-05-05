package com.upgrad.musichoster.api.controller;


import com.upgrad.musichoster.api.model.*;
import com.upgrad.musichoster.service.business.AdminService;
import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.exception.MusicNotFoundException;
import com.upgrad.musichoster.service.exception.UnauthorizedException;
import com.upgrad.musichoster.service.exception.UserNotSignedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.GET, path = "/musics/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MusicDetailsResponse> getMusic(@PathVariable("id") final String musicUuid, @RequestHeader("authorization") final String authorization) throws
            MusicNotFoundException, UnauthorizedException, UserNotSignedInException {

        final MusicEntity musicEntity = adminService.getMusic(musicUuid, authorization);
        MusicDetailsResponse imageDetailsResponse = new MusicDetailsResponse().music(musicEntity.getMusic()).id((int) musicEntity.getId()).name(musicEntity.getName()).description(musicEntity.getDescription()).status(musicEntity.getStatus());

        MusicDetailsResponse musicDetailsResponse = new MusicDetailsResponse().music(musicEntity.getMusic()).id((int) musicEntity.getId()).name(musicEntity.getName()).description(musicEntity.getDescription()).status(musicEntity.getStatus());
        return new ResponseEntity<MusicDetailsResponse>(imageDetailsResponse, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/musics/update/{music_id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdateMusicResponse> updateMusic(@RequestBody(required = false) final UpdateMusicRequest updateMusicRequest, @PathVariable("music_id") final long music_id, @RequestHeader("authorization") final String authorization) throws MusicNotFoundException, UnauthorizedException, UserNotSignedInException {
        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setMusic(updateMusicRequest.getMusic());
        musicEntity.setId(music_id);
        musicEntity.setName(updateMusicRequest.getName());
        musicEntity.setStatus(updateMusicRequest.getStatus());
        musicEntity.setDescription(updateMusicRequest.getDescription());

        MusicEntity updatedMusicEntity = adminService.updateMusic(musicEntity, authorization);
        UpdateMusicResponse updateMusicResponse = new UpdateMusicResponse().id((int) updatedMusicEntity.getId()).status(updatedMusicEntity.getStatus());

        return new ResponseEntity<UpdateMusicResponse>(updateMusicResponse, HttpStatus.OK);

    }

}
