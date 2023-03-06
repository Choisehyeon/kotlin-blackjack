package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackGame
import blackjack.domain.Cards
import blackjack.domain.introduce
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val blackJack = setUpBlackJack()
        outputView.outputInitState(blackJack)
        startBlackJack(blackJack)
        outputView.outputResult(blackJack)
    }

    private fun setUpBlackJack(): BlackJack = introduce {
        cardDeck(Cards.all())
        participants {
            dealer()
            guests(inputView.inputParticipants())
        }
        draw()
    }

    private fun startBlackJack(blackJack: BlackJack) =
        BlackJackGame().apply {
            input(inputView::inputDrawMore)
            blackJack.guestsTurn(outputView::outputCard)
            blackJack.dealerTurn(outputView::outputDealerDraw)
        }
}
