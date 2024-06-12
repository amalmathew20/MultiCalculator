package com.example.Calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() 

{
    override fun onCreate(savedInstanceState: Bundle?) 
    
    {
        super.onCreate(savedInstanceState)
        setContent 
        {
            CalcView()
        }
    }
}

@Composable
fun CalcView() 

{
    val displayText = remember

        { 
        mutableStateOf("0") 
        }
    val currentOperation = remember 
    
        {
             mutableStateOf<String?>(null) 
        }

    val firstOperand = remember 
        { 
        mutableStateOf<String?>(null) 
        }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp)
    ) 
    
    {
        Row 
        
        {
            CalcDisplay(displayText)
        }
        Row 
        
        {
            Column 
            
            {
                for (i in 7 downTo 1 step 3) 
                
                
                {
                    CalcRow(displayText, i, 3)
                }

                Row 
                
                {
                    CalcNumericButton(0, displayText)
                    CalcEqualsButton(displayText, firstOperand, currentOperation)
                }
            }


            Column 
            
            {
                CalcOperationButton("+", displayText, firstOperand, currentOperation)
                CalcOperationButton("-", displayText, firstOperand, currentOperation)
                CalcOperationButton("*", displayText, firstOperand, currentOperation)
                CalcOperationButton("/", displayText, firstOperand, currentOperation)
            }
        }
    }
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) 


{
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) 
    
    
    {
        for (i in startNum until endNum) 
        
        {
            CalcNumericButton(i, display)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) 


{
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        fontSize = 32.sp
    )
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) 

{
    Button(
        onClick = 
        
        {
            if (display.value == "0") 
            
            {
                display.value = number.toString()
            } else 
            
            {
                display.value += number.toString()
            }
        },
        modifier = Modifier.padding(4.dp)
    ) 
    
    
    {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(
    operation: String,
    display: MutableState<String>,
    firstOperand: MutableState<String?>,
    currentOperation: MutableState<String?>
) 

{
    Button(
        onClick = 
        
        {
            firstOperand.value = display.value
            currentOperation.value = operation
            display.value = "0"
        },

        modifier = Modifier.padding(4.dp)
    ) 
    
    {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(
    display: MutableState<String>,
    firstOperand: MutableState<String?>,
    currentOperation: MutableState<String?>
) 

{
    Button(
        onClick = 
        {
            val firstNum = firstOperand.value?.toFloatOrNull()
            val secondNum = display.value.toFloatOrNull()
            if (firstNum != null && secondNum != null && currentOperation.value != null) 
            
            {
                display.value = when (currentOperation.value) 
                
                {
                    "+" -> (firstNum + secondNum).toString()
                    "-" -> (firstNum - secondNum).toString()
                    "*" -> (firstNum * secondNum).toString()
                    "/" -> if (secondNum != 0f) (firstNum / secondNum).toString() else "Error"
                    else -> "Error"
                }
            }
            currentOperation.value = null
            firstOperand.value = null
        },
        modifier = Modifier.padding(4.dp)
    ) 
    
    {
        Text(text = "=")
    }
}
