package jp.kaleidot725.easycalc.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import jp.kaleidot725.easycalc.compose.ComposeNavigation.Category.Companion.getCategory
import jp.kaleidot725.easycalc.compose.ComposeNavigation.Category.Companion.isCategoryRoute
import jp.kaleidot725.easycalc.core.ui.extention.clickableNoRipple
import jp.kaleidot725.easycalc.core.ui.screen.history.HistoryAction
import jp.kaleidot725.easycalc.core.ui.screen.history.HistoryEvent
import jp.kaleidot725.easycalc.core.ui.screen.history.HistoryScreen
import jp.kaleidot725.easycalc.core.ui.screen.history.HistoryViewModel
import jp.kaleidot725.easycalc.core.ui.screen.home.HomeAction
import jp.kaleidot725.easycalc.core.ui.screen.home.HomeEvent
import jp.kaleidot725.easycalc.core.ui.screen.home.HomeScreen
import jp.kaleidot725.easycalc.core.ui.screen.home.HomeViewModel
import jp.kaleidot725.easycalc.core.ui.screen.interrupt.InterruptScreen
import jp.kaleidot725.easycalc.core.ui.screen.mylist.MyListAction
import jp.kaleidot725.easycalc.core.ui.screen.mylist.MyListEvent
import jp.kaleidot725.easycalc.core.ui.screen.mylist.MyListScreen
import jp.kaleidot725.easycalc.core.ui.screen.mylist.MyListViewModel
import jp.kaleidot725.easycalc.core.ui.screen.progress.ProgressEvent
import jp.kaleidot725.easycalc.core.ui.screen.progress.ProgressScreen
import jp.kaleidot725.easycalc.core.ui.screen.progress.ProgressViewModel
import jp.kaleidot725.easycalc.core.ui.screen.quiz.QuizAction
import jp.kaleidot725.easycalc.core.ui.screen.quiz.QuizEvent
import jp.kaleidot725.easycalc.core.ui.screen.quiz.QuizScreen
import jp.kaleidot725.easycalc.core.ui.screen.quiz.QuizViewModel
import jp.kaleidot725.easycalc.core.ui.screen.result.ResultEvent
import jp.kaleidot725.easycalc.core.ui.screen.result.ResultScreen
import jp.kaleidot725.easycalc.core.ui.screen.result.ResultViewModel
import jp.kaleidot725.easycalc.core.ui.screen.setting.SettingScreen
import jp.kaleidot725.easycalc.core.ui.screen.setting.language.LanguageAction
import jp.kaleidot725.easycalc.core.ui.screen.setting.language.LanguageEvent
import jp.kaleidot725.easycalc.core.ui.screen.setting.language.LanguageScreen
import jp.kaleidot725.easycalc.core.ui.screen.setting.language.LanguageViewModel
import jp.kaleidot725.easycalc.core.ui.screen.setting.license.LicenseScreen
import jp.kaleidot725.easycalc.core.ui.screen.setting.privacy.PrivacyPolicyScreen
import jp.kaleidot725.easycalc.core.ui.screen.setting.theme.ThemeAction
import jp.kaleidot725.easycalc.core.ui.screen.setting.theme.ThemeEvent
import jp.kaleidot725.easycalc.core.ui.screen.setting.theme.ThemeScreen
import jp.kaleidot725.easycalc.core.ui.screen.setting.theme.ThemeViewModel
import jp.kaleidot725.easycalc.core.ui.screen.start.StartEvent
import jp.kaleidot725.easycalc.core.ui.screen.start.StartScreen
import jp.kaleidot725.easycalc.core.ui.screen.start.StartViewModel
import jp.kaleidot725.easycalc.core.ui.screen.stats.StatsScreen
import jp.kaleidot725.easycalc.core.ui.screen.stats.StatsViewModel
import jp.kaleidot725.easycalc.feature.category.CategoryAction
import jp.kaleidot725.easycalc.feature.category.CategoryEvent
import jp.kaleidot725.easycalc.feature.category.CategoryScreen
import jp.kaleidot725.easycalc.feature.category.CategoryViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavDestination.visibleBottomNavigation(): Boolean {
    return this.isTextGroup() || this.isSettingGroup() || this.isHomeGroup()
}

fun NavDestination.isDialog(): Boolean {
    fun judge(route: String?): Boolean {
        return route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Interrupt.route
    }
    return this.hierarchy.any { judge(it.route) }
}

fun NavDestination.isHomeGroup(): Boolean {
    fun judge(route: String?): Boolean {
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.History.route) return true
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.MyList.route) return true
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Stats.route) return true
        return hierarchy.any { route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Home.route }
    }
    return this.hierarchy.any { judge(it.route) }
}

fun NavDestination.isTextGroup(): Boolean {
    fun judge(route: String?): Boolean {
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Quiz.route) return true
        return route?.isCategoryRoute() == true
    }
    return this.hierarchy.any { judge(it.route) }
}

fun NavDestination.isSettingGroup(): Boolean {
    fun judge(route: String?): Boolean {
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Setting.route) return true
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.License.route) return true
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.PrivacyPolicy.route) return true
        if (route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Theme.route) return true
        return route == jp.kaleidot725.easycalc.compose.ComposeNavigation.Language.route
    }
    return this.hierarchy.any { judge(it.route) }
}

fun NavGraphBuilder.addHomeScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Home.path) {
        val viewModel = koinViewModel<HomeViewModel>()
        val action = viewModel as HomeAction
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HomeEvent.ClickHistory -> {
                    val route = jp.kaleidot725.easycalc.compose.ComposeNavigation.History.route
                    navController.navigate(route)
                }

                is HomeEvent.ClickMyList -> {
                    val route = jp.kaleidot725.easycalc.compose.ComposeNavigation.MyList.route
                    navController.navigate(route)
                }

                is HomeEvent.ClickText -> {
                    val route =
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(sideEffect.mathText).route
                    navController.navigate(route)
                }

                is HomeEvent.ClickStats -> {
                    val route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Stats.route
                    navController.navigate(route)
                }
            }
        }
        HomeScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addStatsScreen() {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Stats.path) {
        val viewModel = koinViewModel<StatsViewModel>()
        val state by viewModel.collectAsState()
        StatsScreen(
            state = state,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addQuizScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Quiz.path) {
        val viewModel = koinViewModel<QuizViewModel>()
        val action = viewModel as QuizAction
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QuizEvent.ClickCategory -> {
                    val route =
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Category(sideEffect.category).route
                    navController.navigate(route)
                }

                is QuizEvent.ClickText -> {
                    val route =
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(sideEffect.mathText).route
                    navController.navigate(route)
                }
            }
        }
        QuizScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addCategoryScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Category().path) {
        val category = navController.currentBackStackEntry?.getCategory()
        val viewModel = koinViewModel<CategoryViewModel> { parametersOf(category) }
        val action = viewModel as CategoryAction
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is CategoryEvent.ClickText -> {
                    navController.navigate(
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(
                            sideEffect.mathText
                        ).route
                    )
                }

                is CategoryEvent.PopBack -> {
                    navController.popBackStack()
                }
            }
        }
        CategoryScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addMyListScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.MyList.path) {
        val viewModel = koinViewModel<MyListViewModel>()
        val action = viewModel as MyListAction
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is MyListEvent.ClickText -> {
                    navController.navigate(
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(
                            sideEffect.mathText
                        ).route
                    )
                }

                is MyListEvent.PopBack -> {
                    navController.popBackStack()
                }
            }
        }
        MyListScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addHistoryScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.History.path) {
        val viewModel = koinViewModel<HistoryViewModel>()
        val action = viewModel as HistoryAction
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HistoryEvent.ClickText -> {
                    navController.navigate(
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(
                            sideEffect.mathText
                        ).route
                    )
                }

                is HistoryEvent.PopBack -> {
                    navController.popBackStack()
                }
            }
        }
        HistoryScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addStartScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Start().path) {
        val id =
            jp.kaleidot725.easycalc.compose.ComposeNavigation.Start.getTextId(navController.currentBackStackEntry)
        val viewModel = koinViewModel<StartViewModel> { parametersOf(id) }
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is StartEvent.ClickStart -> {
                    navController.navigate(
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Progress(
                            sideEffect.mathText
                        ).route
                    )
                }

                is StartEvent.PopBack -> {
                    navController.popBackStack()
                }
            }
        }
        StartScreen(
            state = state,
            action = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addProgressScreen(
    navController: NavController,
    sound: ComposeAppSound,
    onChangedProgress: (ComposeAppProgress) -> Unit
) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Progress().path) {
        val id =
            jp.kaleidot725.easycalc.compose.ComposeNavigation.Progress.getTextId(navController.currentBackStackEntry)
        val viewModel = koinViewModel<ProgressViewModel> { parametersOf(id) }
        val state by viewModel.collectAsState()

        LaunchedEffect(state) {
            onChangedProgress.invoke(ComposeAppProgress(state.textProgress, state.mathText.count))
        }

        DisposableEffect(Unit) {
            onDispose {
                sound.stopBgm()
            }
        }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ProgressEvent.Interrupted -> {
                    navController.navigate(jp.kaleidot725.easycalc.compose.ComposeNavigation.Interrupt.route)
                }

                is ProgressEvent.Finish -> {
                    navController.navigate(
                        jp.kaleidot725.easycalc.compose.ComposeNavigation.Result(
                            sideEffect.mathText,
                            sideEffect.qalist
                        ).route
                    )
                    if (!sideEffect.isMute) sound.playFinish()
                }

                is ProgressEvent.Success -> {
                    if (!sideEffect.isMute) sound.playSuccess()
                }

                is ProgressEvent.Failed -> {
                    if (!sideEffect.isMute) sound.playFailed()
                }

                is ProgressEvent.Clear -> {
                    if (!sideEffect.isMute) sound.playClear()
                }

                is ProgressEvent.Input -> {
                    if (!sideEffect.isMute) sound.playInput()
                }

                ProgressEvent.StartBGM -> {
                    sound.playBgm()
                }

                ProgressEvent.StopBGM -> {
                    sound.stopBgm()
                }
            }
        }
        ProgressScreen(
            state = state,
            action = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addResultScreen(
    composeAppAction: ComposeAppAction,
    navController: NavController
) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Result().path) {
        val id =
            jp.kaleidot725.easycalc.compose.ComposeNavigation.Result.getTextId(navController.currentBackStackEntry)
        val qaList =
            jp.kaleidot725.easycalc.compose.ComposeNavigation.Result.getQAList(navController.currentBackStackEntry)
        val viewModel = koinViewModel<ResultViewModel> { parametersOf(id, qaList) }
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ResultEvent.PopBack, ResultEvent.Finish -> {
                    jp.kaleidot725.easycalc.compose.ComposeNavigation.Result.clear(navController.currentBackStackEntry)
                    val result = navController.popBackStack(
                        route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Quiz.route,
                        inclusive = false
                    )
                    if (!result) {
                        navController.popBackStack(
                            route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Home.route,
                            inclusive = false
                        )
                    }
                    composeAppAction.finish()
                }

                is ResultEvent.Retry -> {
                    jp.kaleidot725.easycalc.compose.ComposeNavigation.Result.clear(navController.currentBackStackEntry)
                    navController.popBackStack(
                        route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Start(sideEffect.mathText).route,
                        inclusive = false
                    )
                    navController.navigate(
                        route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Progress(
                            sideEffect.mathText
                        ).route
                    )
                }
            }
        }
        ResultScreen(
            state = state,
            action = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addInterruptDialog(navController: NavController) {
    dialog(
        route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Interrupt.path,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        InterruptScreen(
            onDismiss = {
                navController.popBackStack()
            },
            onConfirm = {
                val result = navController.popBackStack(
                    route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Quiz.route,
                    inclusive = false
                )
                if (!result) {
                    navController.popBackStack(
                        route = jp.kaleidot725.easycalc.compose.ComposeNavigation.Home.route,
                        inclusive = false
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.25f))
                .clickableNoRipple { }
        )
    }
}

fun NavGraphBuilder.addSettingScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Setting.path) {
        SettingScreen(
            onNavigateLicense = { navController.navigate(jp.kaleidot725.easycalc.compose.ComposeNavigation.License.route) },
            onNavigateTheme = { navController.navigate(jp.kaleidot725.easycalc.compose.ComposeNavigation.Theme.route) },
            onNavigateLanguage = { navController.navigate(jp.kaleidot725.easycalc.compose.ComposeNavigation.Language.route) },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

fun NavGraphBuilder.addLicenseScreen() {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.License.path) {
        LicenseScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addPrivacyPolicyScreen() {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.PrivacyPolicy.path) {
        PrivacyPolicyScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.addThemeScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Theme.path) {
        val viewModel = koinViewModel<ThemeViewModel>()
        val action = viewModel as ThemeAction
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ThemeEvent.PopBack -> navController.navigateUp()
            }
        }
        ThemeScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

fun NavGraphBuilder.addLanguageScreen(navController: NavController) {
    composable(jp.kaleidot725.easycalc.compose.ComposeNavigation.Language.path) {
        val viewModel = koinViewModel<LanguageViewModel>()
        val action = viewModel as LanguageAction
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                LanguageEvent.PopBack -> navController.navigateUp()
            }
        }
        LanguageScreen(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
