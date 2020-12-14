package todoapp.todo;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;

/**
 * This class provide an ADT for a Deadline Attribute. A Deadline object is an 
 * Attribute that contains an expiration date. It is characterized by a limit 
 * date and time, and a color. The object is mutable.
 */
@EqualsAndHashCode
public class Deadline implements Attribute {

	/**
	 * The Deadline implementation is done with:
	 * - a LocalDateTime variable that represent the deadline date
	 * - a Color variable that represent the color
	 * 
	 * Invariant: the date must be not null and must represent a future date. 
	 * The color must be not null.
	 */
	private LocalDateTime deadline;
	private Color color;
	
	/**
	 * Constructor that specify the date for the deadline. The color is setted 
	 * to the predefined one (blue).
	 * @param date: the date of the Deadline. Required be not null and must 
	 * represent a future date.
	 * @throws NullPointerException when date is null.
	 * @throws PastDateException when date represent a non future date.
	 */
	public Deadline(LocalDateTime date) throws PastDateException {

		if (date == null) {
			throw new NullPointerException();
		} else if (date.isBefore(LocalDateTime.now())) {
			throw new PastDateException();
		}

		this.deadline = date;
		this.color = Color.BLUE;
	}

	/**
	 * Constructor that specify the date for the deadline and its color.
	 * @param date: the date of the Deadline. Required be not null and must 
	 * represent a future date.
	 * @param color: the color of the tag. Required to be not null.
	 * @throws NullPointerException when the date or the color is null.
	 * @throws PastDateException when date represent a non future date.
	 */
	public Deadline(LocalDateTime date, Color color) throws PastDateException {

		if (date == null || color == null) {
			throw new NullPointerException();
		} else if (date.isBefore(LocalDateTime.now())) {
			throw new PastDateException();
		}

		this.deadline = date;
		this.color = color;
	}

	/**
	 * Copy constructor that creates a perfect copy of the Deadline instance passed.
	 * @param d: the Deadline to copy. Required to be not null.
	 * @throws NullPointerException when d is null.
	 */
	public Deadline(Deadline d) {
		if (d == null) {
			throw new NullPointerException();
		}

		this.deadline = d.deadline;
		this.color = d.color;
	}

	/**
	 * @return the date that is contained inside this deadline as text.
	 */
	@Override
	public String getText() {
		return this.deadline.toString();
	}

	/**
	 * @return the color associated with this deadline.
	 */
	@Override
	public Color getColor() {
		return this.color;
	}

	/**
	 * Modify this changing the date of the deadline with a new one.
	 * @param newDate: the new date of the deadline. Required be not null and 
	 * must represent a future date.
	 * @throws NullPointerException if newDate is null.
	 * @throws PastDateException if newDate represent a non future date.
	 */
	public void changeDate(LocalDateTime newDate) throws PastDateException {

		if (newDate == null) {
			throw new NullPointerException();
		} else if (newDate.isBefore(LocalDateTime.now())) {
			throw new PastDateException();
		}

		this.deadline = newDate;
	}

	/**
	 * Modify this changing the color associated with a new one.
	 * @param newColor: the new color for the deadline. Required to be not null.
	 * @throws NullPointerException if newColor is null.
	 */
	@Override
	public void changeColor(Color newColor) {

		if (newColor == null) {
			throw new NullPointerException();
		}

		this.color = newColor;
	}

}	// class Deadline
