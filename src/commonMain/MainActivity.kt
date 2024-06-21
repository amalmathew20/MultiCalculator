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
    val displayText = rememberSaveable 
    { 
    mutableStateOf("0") 
    }
    val leftNumber = rememberSaveable 
    { 
         mutableStateOf(0) 
    }
    val rightNumber = rememberSaveable 
    { 
    mutableStateOf(0) 
    }
    val operation = rememberSaveable 
    { 
        mutableStateOf("") 
    }
    val complete = rememberSaveable 
    { 
    mutableStateOf(false) 
    }

    if (complete.value && operation.value.isNotEmpty()) 
    {
         var answer = 0
        when (operation.value) 
        {
            "+" -> answer = leftNumber.value + rightNumber.value
            "-" -> answer = leftNumber.value - rightNumber.value
            "*" -> answer = leftNumber.value * rightNumber.value
            "/" -> if (rightNumber.value != 0) answer = leftNumber.value / rightNumber.value
        }
        displayText.value = answer.toString()
    } 
    else if (operation.value.isNotEmpty() && !complete.value) 
    {
        displayText.value = rightNumber.value.toString()
    } 
    
    else
     {
        displayText.value = leftNumber.value.toString()
    }

    fun numberPress(btnNum: Int) 
    {
        if (complete.value) 
        {
            leftNumber.value = 0
            rightNumber.value = 0
            operation.value = ""
            complete.value = false
        }

        if (operation.value.isNotEmpty() && !complete.value) 
        {
            rightNumber.value = rightNumber.value * 10 + btnNum
        } 
        
        else if (operation.value.isEmpty() && !complete.value) 
        {
            leftNumber.value = leftNumber.value * 10 + btnNum
        }
    }

    fun operationPress(op: String) 
    
    {
        if (!complete.value) 
        
        {
            operation.value = op
        }
    }

    fun equalsPress() 
    
    {
    complete.value = true
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
                    CalcRow(onPress = { number -> numberPress(number) }, startNum = i, numButtons = 3)
                }

                Row 
                {
                    CalcNumericButton(onPress = { number -> numberPress(number) }, number = 0)
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }
            Column 
            
            {
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "+")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "-")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "*")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "/")
            }
        }
    }
}

@Composable

fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) 
{
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) 
    
    {
        for (i in startNum until endNum) '
        {
            CalcNumericButton(onPress = onPress, number = i)
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
fun CalcNumericButton(onPress: (number: Int) -> Unit, number: Int) 

{
    Button(
        onClick = { onPress(number) },
        modifier = Modifier.padding(4.dp)
    ) 
    {
    Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(onPress: (operation: String) -> Unit, operation: String) 

{
    Button(
        onClick = { onPress(operation) },
        modifier = Modifier.padding(4.dp)
    ) 
    {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) 
{
    Button(
        onClick = { onPress() },
        modifier = Modifier.padding(4.dp)
    ) 
    {
        Text(text = "=")
    }
}
