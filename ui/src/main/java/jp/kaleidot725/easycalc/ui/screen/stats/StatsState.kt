package jp.kaleidot725.easycalc.ui.screen.stats

import jp.kaleidot725.easycalc.domain.model.stats.StatsData

data class StatsState(
    val isLoading: Boolean,
    val items: List<StatsData>
)
