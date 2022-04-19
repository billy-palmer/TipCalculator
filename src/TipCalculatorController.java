
import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TipCalculatorController {

	// formatters for currency and percentages
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat.getPercentInstance();

	NumberFormat taxRate = NumberFormat.getNumberInstance();

	private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
	private BigDecimal taxPercentage = new BigDecimal(0.055); // 5.5% default
	private int clicks = 0;

	@FXML
	private Label tipPercentageLevel;
	@FXML
	private Slider tipPercentageSlider;

	@FXML
	private Label taxPercentageLevel;
	@FXML
	private Slider taxPercentageSlider;

	@FXML
	private TextField amountTextField;
	@FXML
	private TextField baseTextField;
	@FXML
	private TextField taxTextField;
	@FXML
	private TextField tipTextField;

	@FXML
	private TextField totalTextField;
	@FXML
	private HBox cardBox;

	@FXML
	void calculateButtonPressed(ActionEvent event) {
		try {
			BigDecimal base = new BigDecimal(baseTextField.getText());
			BigDecimal tax = base.multiply(taxPercentage);
			BigDecimal amount = base.add(tax);

			BigDecimal tip = amount.multiply(tipPercentage);
			BigDecimal totalAmount = base.add(tax);
			BigDecimal total = amount.add(tip);

			taxTextField.setText(currency.format(tax));
			tipTextField.setText(currency.format(tip));
			amountTextField.setText(currency.format(totalAmount));
			totalTextField.setText(currency.format(total));
		} catch (NumberFormatException ex) {
			baseTextField.setText("Enter amount");
			baseTextField.selectAll();
			baseTextField.requestFocus();
		}
	}

	@FXML
	void getCard(ActionEvent event) {
		clicks++;
		for (int i = 0; i < 10; i++) {
			clicks++;
			if (clicks % 2 == 1) {
				cardBox.setOpacity(1);
			}
			if (clicks % 2 == 0) {
				cardBox.setOpacity(0);
			}

		}

	}

	public void initialize() {

		percent.setMaximumFractionDigits(1);

		tipPercentageSlider.valueProperty().addListener( // listener sees changes and moves along with it
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
						tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0);
						tipPercentageLevel.setText(percent.format(tipPercentage));
					}
				});

		taxPercentageSlider.valueProperty().addListener( // listener sees changes and moves along with it
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
						taxPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100);
						taxPercentageLevel.setText(percent.format(taxPercentage)); // issue here is that it is cutting
																					// off the tenths numbers
					}
				});

	}

}
