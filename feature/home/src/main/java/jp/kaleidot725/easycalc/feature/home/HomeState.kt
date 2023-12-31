package jp.kaleidot725.easycalc.feature.home

import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts

data class HomeState(
    val histories: MathTexts = MathTexts.EMPTY,
    val mylist: MathTexts = MathTexts.EMPTY,
    val todayStreakDays: StatsData.TodayStreakDays? = null,
    val todaySolvedQuizCount: StatsData.TodaySolvedQuizCount? = null,
    val todayTrainingTime: StatsData.TodayTrainingTime? = null
)
