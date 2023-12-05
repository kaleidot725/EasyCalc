package jp.kaleidot725.easycalc.ui.screen.home

import jp.kaleidot725.easycalc.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.domain.model.text.MathTexts

data class HomeState(
    val userName: String = "Albites Software",
    val enableAd: Boolean = true,
    val adUnitId: String = "",
    val histories: MathTexts = MathTexts.EMPTY,
    val mylist: MathTexts = MathTexts.EMPTY,
    val todayStreakDays: StatsData.TodayStreakDays? = null,
    val todaySolvedQuizCount: StatsData.TodaySolvedQuizCount? = null,
    val todayTrainingTime: StatsData.TodayTrainingTime? = null
)
