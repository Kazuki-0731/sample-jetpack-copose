/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sample_jetpack_compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.sample_jetpack_compose.data.AppContainer
import com.example.sample_jetpack_compose.ui.App.Companion.JETNEWS_APP_URI
import com.example.sample_jetpack_compose.ui.home.HomeRoute
import com.example.sample_jetpack_compose.ui.home.HomeViewModel
import com.example.sample_jetpack_compose.ui.interests.InterestsRoute
import com.example.sample_jetpack_compose.ui.interests.InterestsViewModel

const val POST_ID = "postId"

@Composable
fun CatsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = CatsDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = CatsDestinations.HOME_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern =
                        "$JETNEWS_APP_URI/${CatsDestinations.HOME_ROUTE}?$POST_ID={$POST_ID}"
                }
            )
        ) { navBackStackEntry ->
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    postsRepository = appContainer.postsRepository,
                    preSelectedPostId = navBackStackEntry.arguments?.getString(POST_ID)
                )
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )
        }
        composable(CatsDestinations.INTERESTS_ROUTE) {
            val interestsViewModel: InterestsViewModel = viewModel(
                factory = InterestsViewModel.provideFactory(appContainer.interestsRepository)
            )
            InterestsRoute(
                interestsViewModel = interestsViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}