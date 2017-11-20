package com.example.steph.apcsmcquiz5;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Question> questions, specificQuestions;
    private AppDatabase db;
    private Question q;
    private String topic;
    private TextView qText;
    private RadioButton a, b, c, d, e;
    private RadioButton[] choices;
    private String choiceId;
    private Button s;
    private int questionsCompleted;
    public static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (RadioButton)findViewById(R.id.choice1Text);
        b = (RadioButton)findViewById(R.id.choice2Text);
        c = (RadioButton)findViewById(R.id.choice3Text);
        d = (RadioButton)findViewById(R.id.choice4Text);
        e = (RadioButton)findViewById(R.id.choice5Text);

        choices = new RadioButton[]{a,b,c,d,e};

        s = (Button)findViewById(R.id.button);

        qText = (TextView)findViewById(R.id.questionText);

        //This should give back which button is being clicked
        for(int i = 0; i<choices.length; i++) {
            choices[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(v);
                }
            });
        }


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "questions").build();

        questions = new ArrayList<Question>();

        questions.add(new Question(1, "What is the value of y after the following code is executed?\n" + "\n" + "int x = 123, y = 0;\n" +
                "      while (x > 0)\n" + "     {\n" + "        y *= 10;\n" + "        y += x % 10;\n" + "        x /= 10;" + " }", "5", "1", "3", "6", "12", "321", "Iterations"));
        questions.add(new Question(2, "Which of the following arithmetic expressions maps the values x = 0, 1, 2, 3, 4, 5, 6 onto  y = 4, 3, 9, 8, 7, 6, 5, respectively?", "3",
         "y = 11 - x + (x + 4) % 7;", "y = (4 - x) % 7 + 2 * x;", "y = 3 + (8 - x) % 7;", "y = 9 - (x - 2) % 7;", "y = 4 + x % 7 - 2 * x;", "Logic and Operations"));
         questions.add(new Question(3, "Which of the following statements DOES NOT display 9.95", "3", "System.out.println(9 + 0.95);",
         "System.out.println(995/100.0);", "System.out.println(9. + 95/100);", "System.out.println(9 + \".\" + 95);", "System.out.println(9 + 95.0/100);", "Logic and Operations"));
         questions.add(new Question(4, "Given\n" + "\n" + "      Random generator = new Random();\n" + "     int bigNum = 10000;\n" + "     int r = generator.nextInt(bigNum);\n" + "\n" + " which of the following expressions is the best way to initialize x to the value of a randomly chosen element from an array arr of 3 values?  (The odds for choosing any element must be the same or almost the same.)",
         "2", "x = arr[r / bigNum * 3];", "x = arr[(int)(3.0 * r / bigNum)];",
         "x = arr[(int)(2.9 * r / bigNum)];", "x = arr[(int)(3.0 * (r - 1) / (bigNum - 1))];", "x = arr[3 * (int)((double)r / bigNum)];", "Array"));
         questions.add(new Question(5, "What is displayed by\n" + "       System.out.println(\"1\" + new Integer(2) + 3);  ", "4",
         "The statement has a syntax error and won't compile", "6", "15", "123", "ClassCastException", "Object Class"));
         questions.add(new Question(6, "A car dealership needs a program to store information about the cars for sale. For each car, they want to keep track of the following information: number of doors (2 or 4), whether the car has air conditioning, and its average number of miles per gallon. Which of the following is the best object-oriented program design?", "1", "Use one class, Car, with three instance variables: int numDoors, boolean hasAir, and double milesPerGallon.",
         "Use four unrelated classes: Car, Doors, AirConditioning, and MilesPerGallon.", "Use a class Car with three subclasses: Doors, AirConditioning, and MilesPerGallon.", "Use a class Car, with a subclass Doors, with a subclass AirConditioning, with a subclass MilesPerGallon.",
         "Use three classes: Doors, AirConditioning, and MilesPerGallon each with a subclass Car.", "Object Class"));
         questions.add(new Question(7, "Consider the following declarations.\n" +
         "public interface Shape\n" +
         "{\n" +
         "int isLargerThan(Shape other);\n" +
         "// Other methods not shown\n" +
         "}\n" +
         "public class Circle implements Shape\n" +
         "{\n" +
         "// Other methods not shown\n" +
         "}\n" +
         "Which of the following method headings of isLargerThan can be added to the declaration of the Circle class so that it will satisfy the Shape interface?\n" +
         "I. public int isLargerThan(Shape other)\n" +
         "II. public int isLargerThan(Circle other)\n" +
         "III. public boolean isLargerThan(Object other)", "1", "I only", "II only", "III only", "I and II only", "I, II, and III", "Object Class"));
         questions.add(new Question(8, "What is the output from the following statement?\n" + "       System.out.println(new Double((new Integer(200)).intValue() / 6).doubleValue());  ",
         "2", "33", "33.0", "33.334", "33.333333333333336", "ClassCastException", "Object Class"));
         questions.add(new Question(9, "What is the result when\n" + "       System.out.println((int)Double.parseDouble(\".9\"));\n" + " is compiled/executed?  ",
         "4", "Syntax error", "ClassCastException", "NumberFormatException", "0", "1", "Object Class"));
         questions.add(new Question(10, "What is the output from the following code segment?\n" + "       String s = \"xoxoxo\";\n" + "      System.out.println(s.substring(s.indexOf(s.substring(2)), s.indexOf(s.substring(3)))); ",
         "1", "x", "o", "xo", "oxoxo", "xoxoxo", "Strings"));
         questions.add(new Question(11, "What is the output from the following code?\n" + "       String s = \"ONION\";\n" + "      System.out.println(s.substring(1, 5).substring(1, 4).substring(0, 3));  ",
         "3", "I", "IO", "ION", "ONI", "NION", "Strings"));
         questions.add(new Question(12, "Which of the following statements is true?", "4", "A static variable cannot be initialized in a constructor. ",
         " A static variable must be declared final. ", "An instance variable can’t be declared final. ", "A static method can’t access an instance variable. ",
         "Only a static method can access a static variable.", "Object Class"));
         questions.add(new Question(13, "Consider the following instance variable and method.\n" +
         "\n" +
         "private int[] numbers;\n" +
         "/** Precondition: numbers contains int values in no particular order.\n" +
         "*\n" +
         "public int mystery(int num)\n" +
         "{\n" +
         "for (int k = numbers.length − 1; k >= 0; k−−)\n" +
         "{\n" +
         "if (numbers[k] < num)\n" +
         "{\n" +
         "return k;\n" +
         "}\n" +
         "}\n" +
         "return -1;\n" +
         "}\n" +
         "Which of the following best describes the contents of numbers after the following statement has been executed?\n" +
         "int m = mystery(n);", "3", "All values in positions 0 through m are less than n.", "All values in positions m+1 through numbers.length-1 are less than n.",
         "All values in positions m+1 through numbers.length-1 are greater than or equal to n.",
         "The smallest value is at position m.", "The largest value that is smaller than n is at position m.", "Iterations"));
         questions.add(new Question(14, "Consider the following method.\n" +
         "\n" +
         "/** @param x an int value such that x >= 0\n" +
         "*\n" +
         "public void mystery(int x)\n" +
         "{\n" +
         "System.out.print(x % 10);\n" +
         "if ((x / 10) != 0)\n" +
         "{\n" +
         "mystery(x / 10);\n" +
         "}\n" +
         "System.out.print(x % 10);\n" +
         "}\n" +
         "\n" +
         "Which of the following is printed as a result of the call mystery(1234)?", "4", "1234", "4321", "12344321", "43211234", "Many digits are printed due to infinite recursion.", "Logic and Operations"));
         questions.add(new Question(15, "What is the value of i after the following code is executed?\n" + "     int i = 0, n = 100;\n" + "     for (i++; i < n; n--)\n" + "        i++;",
         "3", "49", "50", "51", "99", "100", "Iterations"));
         questions.add(new Question(16, "What is the output from\n" + "     String s = \"La\";\n" + "     s.toUpperCase();\n" + "     System.out.println(s + s.toLowerCase())",
         "1", "Lala", "LAla", "LALA", "LaLa", "La la", "String"));
         questions.add(new Question(17, "Consider the following instance variable and method.\n" +
         "private int[] arr;\n" +
         "/** Precondition: arr.length > 0\n" +
         "*\n" +
         "public int checkArray()\n" +
         "{\n" +
         "int loc = arr.length / 2;\n" +
         "for (int k = 0; k < arr.length; k++)\n" +
         "{\n" +
         "if (arr[k] > arr[loc])\n" +
         "{\n" +
         "loc = k;\n" +
         "}\n" +
         "}\n" +
         "return loc;\n" +
         "}\n" +
         "Which of the following is the best postcondition for checkArray ?", "4", "Returns the index of the first element in array arr whose value is greater than arr[loc]", "Returns the index of the last element in array arr whose value is greater than array[loc]",
         "Returns the largest value in array arr", "Returns the index of the largest value in array arr", "Returns the index of the largest value in the second half of array arr", "Arrays"));
         questions.add(new Question(18, "Given\n" + "     String s = \"SOLD\";\n" + " which of the following calls does NOT return 1?",
         "2", "s.lastIndexOf(\"OLD\");", "s.lastIndexOf(\"OLD\", 0);", "s.lastIndexOf(\"OLD\", 1);", "s.lastIndexOf(\"OLD\", 2);", "s.lastIndexOf(\"OLD\", 3)", "String"));
         questions.add(new Question(19, "Which of the following statements is equivalent to\n" + "     if (a == 0 || b*b - 4*a*c <= 0)\n" + "       return false;\n" + "     else\n" + "       return true;", "4",
         "return a == 0 || b*b - 4*a*c <= 0;", "return a == 0 && b*b - 4*a*c > 0;", "return a != 0 || b*b - 4*a*c > 0;", "return a != 0 && b*b - 4*a*c > 0", "None of the above ", "Logic and Operations"));
         questions.add(new Question(20, "Which of the following statements DOES NOT print 2006?", "4", "System.out.println(\"200\" + 6);",
         "System.out.println(200 + \"6\")", "System.out.println((int)(2006 * 1.0000002))", "System.out.println(2006 * 2000000 / 2000000);", "System.out.println(2006 / 1000 * 1000 + 2006 % 1000)", "Logic adn Operations"));
         questions.add(new Question(21, "What is the output from the following statement?/n" + "     System.out.println(\"123\".substring(1) + \"123\".substring(2) + \"123\".substring(3)); ",
         "3", "23", "123", "233", "123233", "112123", "String"));
         questions.add(new Question(22, "What is the size of a double variable in Java?", "3", "2 bytes", "4 bytes", "8 bytes",
         "It depends on the compiler setting", "It depends on the operating system", "Object Class"));
         questions.add(new Question(23, "What is displayed by\n" + "     System.out.println(\"1\" + new Integer(2) + 3)", "4", "The statement has a syntax error and won't compile",
         "6", "15", "123", "ClassCastException", "Object Class"));
         questions.add(new Question(24, "Which of the following best describes the set of all pairs of values for boolean variables a and b, such that\n" + "     (!a && b) == !(a || b)\n" + " evaluates to true? ",
         "3", "Empty set", "Only one pair: a == true, b == false", "Two pairs in which a== true ", "Two pairs in which a != b", "All four possible combinations of values", "Logic and Operations"));
         questions.add(new Question(25, "What is the value of a[1] after the following code is executed?\n" + "     int[] a = {0, 2, 4, 1, 3};\n" + "     for (int i = 0; i < a.length; i++)\n" + "       a[i] = a[(a[i] + 3) % a.length]",
         "2", "0", "1", "2", "3", "4", "Arrays"));
         questions.add(new Question(26, "What is the value of n after the following code is executed?\n" + "     int n = 2005;\n" + "     for (int i = 0; i < 50; i++)\n" + "       n = (n + 3) / 2;",
         "4", "0", "1", "2", "3", "65", "Iterations"));
         questions.add(new Question(27, "Given\n" + "     double x = 5, y = 2;\n" + " what is the value of m after the following statement is executed?\n" + "     int m = (int)(x + y + x / y - x * y - x / (10*y));",
         "1", "-1", "-0.75", "-0.5", "0", "1", "Logic and Operations"));
         questions.add(new Question(28, "What are the values in arr after the following statements are executed?\n" + "     int arr[] = {1, 1, 0, 0, 0};\n" + "     for (int i = 2; i < arr.length; i++)\n" + "       arr[i] += arr[i-1] + arr[i-2]; ",
         "2", "1 1 0 1 1", "1 1 2 1 0", "1 1 2 2 2", "1 1 2 3 8", "1 1 2 3 5", "Arrays"));
         questions.add(new Question(29, "What is the value of sum after the following code segment is executed?\n" + "     int p = 3, q = 1, sum = 0;\n" + "     while (p <= 10)\n" + "     {\n" + "       sum += p % q;\n" + "       p++;\n" + "       q++;\n" + "     } ",
         "3", "0", "10", "12", "14", "52", "Iterations"));
         questions.add(new Question(30, "The method xProperty is defined as follows:\n" + "   public boolean xProperty(int a)\n" + "   {\n" + "     return a == 2 * (a / 10 + a % 10);\n" + "   }\n" + "For which of the following values of a does xProperty(a) return true?",
         "3", "2", "8", "18", "28", "128", "Logic and Operations"));

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                db.questionDao().insertAll(questions);
            }
        };

        Thread addQuestions = new Thread(r1);
        addQuestions.start();

        topic = StartActivity.topic;
        questionsCompleted = 0;

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                if(topic!=null){
                    specificQuestions = db.questionDao().getQuestions(topic);
                }
                else{
                    specificQuestions = db.questionDao().getAll();
                }
                newQuestion();
            }
        };


        Thread getQuestions = new Thread(r2);
        getQuestions.start();



    }

    public void newQuestion(){
        q = specificQuestions.get(questionsCompleted);
        qText.setText(q.getQuestion());
        a.setText(q.getChoice1());
        b.setText(q.getChoice2());
        c.setText(q.getChoice3());
        d.setText(q.getChoice4());
        e.setText(q.getChoice5());
        questionsCompleted++;
    }

    public void click(View v){
        switch(v.getId()){
            case R.id.choice1Text:
                choiceId = "1";
                break;
            case R.id.choice2Text:
                choiceId = "2";
                break;
            case R.id.choice3Text:
                choiceId = "3";
                break;
            case R.id.choice4Text:
                choiceId = "4";
                break;
            case R.id.choice5Text:
                choiceId = "5";
                break;
        }
        if (choiceId.equals(q.getAnswer())){
            score++;
        }
        if(questionsCompleted<specificQuestions.size()){
            newQuestion();
        }
        else{
            Intent intent = new Intent(this, EndPage.class);
            startActivity(intent);
        }
    }

}
