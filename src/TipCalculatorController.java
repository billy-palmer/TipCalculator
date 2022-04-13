

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.stream.IntStream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
//actual assignment
public class TipCalculatorController {
	
	// formatters for currency and percentages
	   private static final NumberFormat currency = 
	      NumberFormat.getCurrencyInstance();
	   private static final NumberFormat percent = 
	      NumberFormat.getPercentInstance();
	   
	   NumberFormat taxRate = NumberFormat.getNumberInstance();
	 
	   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
	   private BigDecimal taxPercentage = new BigDecimal(0.055); // 5.5% default
	   private int clicks=0;
   
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField baseTextField;
  
    @FXML
    private TextField totalTextField;
    @FXML
    private HBox cardBox;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
    	try {
    		String numString = new String(baseTextField.getText());
    		String[] stringNumArray = numString.split(" ");
    		int[] ints = new int[stringNumArray.length];
    		
    		for(int i =0; i<stringNumArray.length; i++){
    			ints[i] = Integer.parseInt(stringNumArray[i]);
    		}
    		BigDecimal count = new BigDecimal(ints.length);
    		BigDecimal sum = new BigDecimal(IntStream.of(ints).sum());
    		BigDecimal avg = sum.divide(count);
    
    		totalTextField.setText(avg+"");
    		
            BigDecimal base = new BigDecimal(baseTextField.getText());
            BigDecimal tax = base.multiply(taxPercentage);
            BigDecimal amount = base.add(tax);
            
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal totalAmount = base.add(tax);
            BigDecimal total = amount.add(tip);

         
            amountTextField.setText(currency.format(totalAmount));
            totalTextField.setText(currency.format(total));
         }
         catch (NumberFormatException ex) {
            baseTextField.selectAll();
            baseTextField.requestFocus();
         }
      }
    @FXML
    void getCard(ActionEvent event) {
    	clicks++;
    	for (int i = 0; i<10;i++)
    	{
    		clicks++;
    	if (clicks%2==1) {
    		cardBox.setOpacity(1);
    	}
     if (clicks%2==0)
    	{
    		cardBox.setOpacity(0);
    	}
  
    	}
  
    
      }
    
 
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up 
    	
       // currency.setRoundingMode(RoundingMode.HALF_UP);
    	 percent.setMaximumFractionDigits(1);
        // listener for changes to tipPercentageSlider's value
      

     
    }

    }




