package com.capstone.yukonek.mainscreen
import CoroutineTestRule
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.RuleChain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val coroutineRule = CoroutineTestRule()
    private lateinit var viewModel: MainViewmodel
    private val yuKonekRepository: YuKonekRepository = mockk(relaxed = true)

    @get:Rule
    val rule = RuleChain.outerRule(coroutineRule)

    @Before
    fun setup() {
        viewModel = MainViewmodel(yuKonekRepository)
    }

    @Test
    fun `verify getThemeSettings calls repository`() {
        coEvery { yuKonekRepository.getTheme() } returns flowOf(true) // Assuming getTheme returns a Boolean
        runBlockingTest {
            val theme = viewModel.getThemeSettings().first()

            verify(exactly = 1) { yuKonekRepository.getTheme() }
            Assert.assertTrue("Expected theme to be true", theme)
        }

    }

    @Test
    fun `verify setThemeSettings calls saveTheme on repository`() = runBlockingTest {
        coEvery { yuKonekRepository.saveTheme(any()) } returns Unit // Setup the mock

        viewModel.setThemeSettings(true)

        coVerify(exactly = 1) { yuKonekRepository.saveTheme(true) } // Verify that the function was called correctly
    }
}