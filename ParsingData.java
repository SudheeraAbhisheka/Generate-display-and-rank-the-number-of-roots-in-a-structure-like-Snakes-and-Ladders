import java.util.*;
import java.io.*;

public class ParsingData
{
    private String[] nodeArray;
    private String[] typesArray;
    private String[] edgeArrayFirst;
    private String[] edgeArraySecond;
    private String[] edgeType;
    private String[] ncode;
    private int[] ncodeValue;
    private String[] ecode;
    private int[] ecodeValue;
    private String start;
    private String[] target;
    private int indOne = 0;
    private int indTwo = 0;
    private int indThree = 0;
    private int indFour = 0;
    private int indFive = 0;
    
    public ParsingData(String pFileName){
        int count;

        count = readFile(pFileName, true);
        nodeArray = new String[count]; 
        typesArray = new String[count]; 
        edgeArrayFirst = new String[count]; 
        edgeArraySecond = new String[count];
        edgeType = new String[count];  
        target = new String[count];
        ncode = new String[count];
        ncodeValue = new int[count];
        ecode = new String[count];
        ecodeValue = new int[count];

        readFile(pFileName, false);
    }

    private int readFile(String pFileName, boolean wantCount) 
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        
        lineNum    = 0;
        try 
        {
            fileStream = new FileInputStream(pFileName);
            rdr        = new InputStreamReader(fileStream);
            bufRdr     = new BufferedReader(rdr);
            line       = bufRdr.readLine();

            if(wantCount){
                while(line != null){
                    line = bufRdr.readLine();
                    lineNum++;
                }
            }
            else{
                while(line != null)
                {
                    process(line);
                    line = bufRdr.readLine();
                }
            }

            fileStream.close();
        }
        catch(IOException errorDetails) 
        {
            if(fileStream != null) 
            {
                try 
                {
                    fileStream.close();
                }
                catch(IOException ex2) 
                { }
            }
            System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
        }
        return lineNum;
    }
    private void process(String row)
    {
        String[] splitLine;
        splitLine = row.split(" ");

        if(splitLine[0].equals("Node")){
            nodeArray[indOne] = splitLine[1];
            typesArray[indOne] = splitLine[2];
            indOne++;
        }
        else if(splitLine[0].equals("Edge")){
            edgeArrayFirst[indTwo] = splitLine[1];
            edgeArraySecond[indTwo] = splitLine[2];
            edgeType[indTwo] = splitLine[3];
            indTwo++;
        }
        else if(splitLine[0].equals("Start")){
            start = splitLine[1];
        }
        else if(splitLine[0].equals("Target")){
            target[indThree] = splitLine[1];
            indThree++;
        }
        else if(splitLine[0].equals("Ncode")){
            ncode[indFour] = splitLine[1];
            ncodeValue[indFour] = Integer.parseInt(splitLine[2]);
            indFour++;
        }
        else if(splitLine[0].equals("Ecode")){
            ecode[indFive] = splitLine[1];
            ecodeValue[indFive] = Integer.parseInt(splitLine[2]);
            indFive++;
        }
    }
    
    public String[] getNodeArray(){
        return nodeArray;
    }  
    public String[] getTypesArray(){
        return typesArray;
    }
    public int getNodeCount(){
        return indOne;
    }
    public String[] getEdgeArrayFirst(){
        return edgeArrayFirst;
    }
    public String[] getEdgeArraySecond(){
        return edgeArraySecond;
    }
    public String[] getEdgeType(){
        return edgeType;
    }
    public int getEdgeCount(){
        return indTwo;
    }
    public String getStart(){
        return start;
    }
    public String[] getTarget(){
        return target;
    }
    public int getTargetCount(){
        return indThree;
    }
    public String[] getNcode(){
        return ncode;
    }
    public int[] getNcodeValue(){
        return ncodeValue;
    }
    public int getNcodeCount(){
        return indFour;
    }   
    public String[] getEcode(){
        return ecode;
    }
    public int[] getEcodeValue(){
        return ecodeValue;
    }
    public int getEcodeCount(){
        return indFive;
    }  
}
