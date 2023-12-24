package jp.kaleidot725.easycalc.core.repository.di

import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelectorImpl
import jp.kaleidot725.easycalc.core.repository.LanguageRepository
import jp.kaleidot725.easycalc.core.repository.SettingRepository
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import jp.kaleidot725.easycalc.core.repository.ThemeRepository
import jp.kaleidot725.easycalc.core.repository.impl.LanguageRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.impl.SettingRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.impl.StatsRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.impl.TextRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.impl.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<ThemeRepository> {
        ThemeRepositoryImpl(androidContext())
    }
    single<LanguageRepository> {
        LanguageRepositoryImpl(androidContext())
    }
    single<SettingRepository> {
        SettingRepositoryImpl(androidContext())
    }
    single<TextRepository> {
        TextRepositoryImpl(androidContext())
    }
    single<QuestionSelector> {
        QuestionSelectorImpl()
    }
    single<StatsRepository> {
        StatsRepositoryImpl(
            get()
        )
    }
}