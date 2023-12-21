package jp.kaleidot725.easycalc.core.ui.screen.main

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import jp.kaleidot725.easycalc.ui.R

class ComposeAppSound {
    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()

    private val soundPool = SoundPool.Builder()
        .setAudioAttributes(audioAttributes)
        .setMaxStreams(6)
        .build()

    private lateinit var mediaPlayer: MediaPlayer

    private var successId: Int = 0
    private var failedId: Int = 0
    private var finishId: Int = 0
    private var inputId: Int = 0
    private var clearId: Int = 0
    private var bgmId: Int = 0
    private var isLoaded = false

    fun load(context: Context) {
        successId = soundPool.load(context, R.raw.success, 0)
        failedId = soundPool.load(context, R.raw.failed, 0)
        finishId = soundPool.load(context, R.raw.finish, 0)
        inputId = soundPool.load(context, R.raw.input, 0)
        clearId = soundPool.load(context, R.raw.clear, 0)
        bgmId = soundPool.load(context, R.raw.bgm, 0)
        mediaPlayer = MediaPlayer.create(context, R.raw.bgm).apply {
            isLooping = true
            setVolume(0.5f, 0.5f)
        }
        isLoaded = true
    }

    fun playSuccess() {
        if (!isLoaded) return
        soundPool.play(successId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun playFailed() {
        if (!isLoaded) return
        soundPool.play(failedId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun playFinish() {
        if (!isLoaded) return
        soundPool.play(finishId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun playInput() {
        if (!isLoaded) return
        soundPool.play(inputId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun playClear() {
        if (!isLoaded) return
        soundPool.play(clearId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun playBgm() {
        if (!isLoaded) return
        if (mediaPlayer.isPlaying) return
        mediaPlayer.seekTo(0)
        mediaPlayer.start()
    }

    fun stopBgm() {
        mediaPlayer.pause()
    }

    fun release() {
        soundPool.release()
        mediaPlayer.release()
        isLoaded = false
    }
}
