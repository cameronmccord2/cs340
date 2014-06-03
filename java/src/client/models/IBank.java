package client.models;

/**
 * The Interface IBank.
 */
public interface IBank extends IParticipant {


	/**
	 * Sets up new bank. This initializes the bank with the correct resource and development cards.
	 */
	void setUpNewBank();

	/**
	 * Draws a random dev card from the available cards in the bank
	 * This method removes the returned card from the bank when called.
	 *
	 * @return DevelopmentCard random card from the bank
	 */
	DevelopmentCard drawRandomDevCard();


}
