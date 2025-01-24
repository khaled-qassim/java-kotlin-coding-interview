import com.hadsol.enums.RPSResult
import com.hadsol.service.GamePlay
import enums.Move
import com.hadsol.logic.GameRules
import model.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GamePlayTest {

    @Mock
    private lateinit var playerA: Player

    @Mock
    private lateinit var playerB: Player

    @Mock
    private lateinit var rules: GameRules

    private lateinit var gamePlay: GamePlay
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gamePlay = GamePlay(playerA, playerB, rules)
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `test all wins scenario`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.SCISSORS)
        `when`(rules.determineResult(Move.ROCK, Move.SCISSORS)).thenReturn(RPSResult.WIN)

        // When
        gamePlay.play(100)

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 100 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }

    @Test
    fun `test all losses scenario`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.SCISSORS)
        `when`(playerB.makeMove()).thenReturn(Move.ROCK)
        `when`(rules.determineResult(Move.SCISSORS, Move.ROCK)).thenReturn(RPSResult.LOSE)

        // When
        gamePlay.play(100)

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 0 of 100 games"))
        assertTrue(output.contains("Player B wins 100 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }

    @Test
    fun `test all draws scenario`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.ROCK)
        `when`(rules.determineResult(Move.ROCK, Move.ROCK)).thenReturn(RPSResult.DRAW)

        // When
        gamePlay.play(100)

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 0 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 100 of 100 games"))
    }

    @Test
    fun `test mixed results scenario`() {
        // Given
        var count = 0
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.SCISSORS)
        `when`(rules.determineResult(Move.ROCK, Move.SCISSORS)).thenAnswer {
            when (count++ % 3) {
                0 -> RPSResult.WIN
                1 -> RPSResult.LOSE
                else -> RPSResult.DRAW
            }
        }

        // When
        gamePlay.play(99) // 99 rounds for even distribution

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 33 of 100 games"))
        assertTrue(output.contains("Player B wins 33 of 100 games"))
        assertTrue(output.contains("Draws: 33 of 100 games"))
    }

    @Test
    fun `test custom rounds number`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.SCISSORS)
        `when`(rules.determineResult(Move.ROCK, Move.SCISSORS)).thenReturn(RPSResult.WIN)

        // When
        gamePlay.play(50)

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 50 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }

    @Test
    fun `test zero rounds`() {
        // When
        assertDoesNotThrow { gamePlay.play(0) }

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 0 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }

    @Test
    fun `test output format`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.SCISSORS)
        `when`(rules.determineResult(Move.ROCK, Move.SCISSORS)).thenReturn(RPSResult.WIN)

        // When
        gamePlay.play(10)

        // Then
        val expectedOutput = """
            Player A wins 10 of 100 games
            Player B wins 0 of 100 games
            Draws: 0 of 100 games
        """.trimIndent()

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim())
    }

    @Test
    fun `test negative rounds`() {
        // When
        assertDoesNotThrow { gamePlay.play(-10) }

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 0 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }

    @Test
    fun `test extremely high number of rounds`() {
        // Given
        `when`(playerA.makeMove()).thenReturn(Move.ROCK)
        `when`(playerB.makeMove()).thenReturn(Move.SCISSORS)
        `when`(rules.determineResult(Move.ROCK, Move.SCISSORS)).thenReturn(RPSResult.WIN)

        // When
        gamePlay.play(10000)

        // Then
        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Player A wins 10000 of 100 games"))
        assertTrue(output.contains("Player B wins 0 of 100 games"))
        assertTrue(output.contains("Draws: 0 of 100 games"))
    }
}