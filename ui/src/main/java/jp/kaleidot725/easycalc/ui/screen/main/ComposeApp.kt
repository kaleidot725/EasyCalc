package jp.kaleidot725.easycalc.ui.screen.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.easycalc.domain.model.text.MathText
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.navigation.SimpleBottomNavigation
import jp.kaleidot725.easycalc.ui.component.navigation.SimpleBottomNavigationItem
import jp.kaleidot725.easycalc.ui.component.navigation.SimpleTopBar
import jp.kaleidot725.easycalc.ui.extention.clickableNoRipple
import jp.kaleidot725.easycalc.ui.screen.main.ComposeNavigation.Category.Companion.getCategory
import jp.kaleidot725.easycalc.ui.screen.main.ComposeNavigation.Category.Companion.isCategoryRoute
import jp.kaleidot725.easycalc.ui.screen.main.ComposeNavigation.Progress.Companion.isProgressRoute
import jp.kaleidot725.easycalc.ui.screen.main.ComposeNavigation.Result.Companion.isResultRoute
import jp.kaleidot725.easycalc.ui.screen.main.ComposeNavigation.Start.Companion.isStartRoute
import jp.kaleidot725.easycalc.ui.screen.resources.MathTextResource

@Composable
fun ComposeApp(
    appState: ComposeAppState,
    appAction: ComposeAppAction,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val state by navController.currentBackStackEntryAsState()
    val isDialog = state?.destination?.isDialog() == true
    val visibleBottomBar by remember { derivedStateOf { state?.destination?.visibleBottomNavigation() == true } }
    val sound by remember { mutableStateOf(ComposeAppSound()) }
    var progress by remember { mutableStateOf(ComposeAppProgress()) }

    DisposableEffect(context) {
        sound.load(context)
        onDispose { sound.release() }
    }

    ComposeTheme(theme = appState.theme, enableBackgroundFilter = appState.isLoading || isDialog, modifier = modifier) {
        Box {
            Scaffold(
                topBar = { TopBar(navController, progress) },
                bottomBar = if (visibleBottomBar) {
                    { BottomBar(navController) }
                } else {
                    {}
                },
                content = {
                    NavHost(
                        navController = navController,
                        startDestination = ComposeNavigation.Home.route,
                        enterTransition = { fadeIn() },
                        popEnterTransition = { fadeIn() },
                        exitTransition = { fadeOut() },
                        popExitTransition = { fadeOut() },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        addHomeScreen(navController)
                        addStatsScreen()
                        addQuizScreen(navController)
                        addCategoryScreen(navController)
                        addHistoryScreen(navController)
                        addStartScreen(navController)
                        addSettingScreen(navController)
                        addLicenseScreen()
                        addPrivacyPolicyScreen()
                        addThemeScreen(navController)
                        addLanguageScreen(navController)
                        addProgressScreen(navController, sound) { p -> progress = p }
                        addResultScreen(appAction, navController)
                        addInterruptDialog(navController)
                        addMyListScreen(navController)
                    }
                }
            )

            if (appState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(0.25f))
                        .clickableNoRipple { Unit }
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier
                            .size(128.dp)
                            .align(Alignment.Center)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController, progress: ComposeAppProgress) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val title: String?
    val onBack: (() -> Unit)?

    when {
        currentDestination?.route == ComposeNavigation.Home.route -> {
            title = stringResource(id = R.string.home_title)
            onBack = null
        }

        currentDestination?.route == ComposeNavigation.Stats.route -> {
            title = stringResource(id = R.string.home_stats_title)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route == ComposeNavigation.Quiz.route -> {
            title = stringResource(R.string.quiz_title)
            onBack = null
        }

        currentDestination?.route == ComposeNavigation.History.route -> {
            title = stringResource(R.string.category_history)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route?.isCategoryRoute() == true -> {
            val category = navController.currentBackStackEntry?.getCategory()!!
            title = when (category) {
                MathText.Category.ADDITION -> stringResource(id = R.string.category_addition)
                MathText.Category.SUBTRACTION -> stringResource(id = R.string.category_subtraction)
                MathText.Category.MULTIPLICATION -> stringResource(id = R.string.category_multiplication)
                MathText.Category.MULTIPLICATION_TABLE -> stringResource(
                    id = R.string.category_multiplication_table
                )

                MathText.Category.DIVISION -> stringResource(id = R.string.category_division)
            }
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route?.isStartRoute() == true -> {
            val textId = ComposeNavigation.Start.getTextId(navController.currentBackStackEntry)
            title = MathTextResource.getTitleById(textId = textId)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route?.isProgressRoute() == true -> {
            title = stringResource(id = R.string.progress_title, progress.current, progress.totalCount)
            onBack = {
                navController.navigate(ComposeNavigation.Interrupt.route)
            }
        }

        currentDestination?.route?.isResultRoute() == true -> {
            title = stringResource(id = R.string.result_title)
            onBack = {
                ComposeNavigation.Result.clear(navController.currentBackStackEntry)
                navController.popBackStack(
                    route = ComposeNavigation.Quiz.route,
                    inclusive = false
                )
            }
        }

        currentDestination?.route == ComposeNavigation.Setting.route -> {
            title = stringResource(id = R.string.setting_title)
            onBack = null
        }

        currentDestination?.route == ComposeNavigation.License.route -> {
            title = stringResource(id = R.string.license_title)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route == ComposeNavigation.PrivacyPolicy.route -> {
            title = stringResource(id = R.string.privacy_policy_title)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route == ComposeNavigation.Theme.route -> {
            title = stringResource(id = R.string.theme_title)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route == ComposeNavigation.Language.route -> {
            title = stringResource(id = R.string.language_title)
            onBack = { navController.popBackStack() }
        }

        currentDestination?.route == ComposeNavigation.MyList.route -> {
            title = stringResource(id = R.string.mylist_title)
            onBack = null
        }

        else -> {
            title = ""
            onBack = null
        }
    }

    SimpleTopBar(title = title, onBack = onBack)
}

@Composable
private fun BottomBar(navController: NavController) {
    SimpleBottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        SimpleBottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text(stringResource(id = R.string.home_title)) },
            selected = currentDestination?.isHomeGroup() ?: false,
            onClick = {
                navController.navigate(ComposeNavigation.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        SimpleBottomNavigationItem(
            icon = { Icon(Icons.Filled.Book, contentDescription = null) },
            label = { Text(stringResource(id = R.string.quiz_title)) },
            selected = currentDestination?.isTextGroup() ?: false,
            onClick = {
                navController.navigate(ComposeNavigation.Quiz.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        SimpleBottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
            label = { Text(stringResource(id = R.string.setting_title)) },
            selected = currentDestination?.isSettingGroup() ?: false,

            onClick = {
                navController.navigate(ComposeNavigation.Setting.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
