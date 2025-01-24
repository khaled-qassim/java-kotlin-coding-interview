import com.hadsol.enums.RPSResult
import com.hadsol.logic.RPS3Rules
import enums.Move
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RPS3RulesTest {

    private val RPS3Rules = RPS3Rules()

    @Test
    fun `test determineResult with Rock vs Scissors`() {
        val result = RPS3Rules.determineResult(Move.ROCK, Move.SCISSORS)
        assertEquals(RPSResult.WIN, result)
    }

    @Test
    fun `test determineResult with Scissors vs Paper`() {
        val result = RPS3Rules.determineResult(Move.SCISSORS, Move.PAPER)
        assertEquals(RPSResult.WIN, result)
    }

    @Test
    fun `test determineResult with Paper vs Rock`() {
        val result = RPS3Rules.determineResult(Move.PAPER, Move.ROCK)
        assertEquals(RPSResult.WIN, result)
    }

    @Test
    fun `test determineResult with Rock vs Rock`() {
        val result = RPS3Rules.determineResult(Move.ROCK, Move.ROCK)
        assertEquals(RPSResult.DRAW, result)
    }

    @Test
    fun `test determineResult with Paper vs Scissors`() {
        val result = RPS3Rules.determineResult(Move.PAPER, Move.SCISSORS)
        assertEquals(RPSResult.LOSE, result)
    }
}