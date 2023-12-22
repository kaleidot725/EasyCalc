package jp.kaleidot725.easycalc.feature.stats

import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData

data class StatsState(
    val isLoading: Boolean,
    val items: List<StatsData>
)
