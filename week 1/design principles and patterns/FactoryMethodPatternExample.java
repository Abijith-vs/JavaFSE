
interface Document {
    void print();
}

interface WordDocument extends Document {
    void printWord();
}

interface PdfDocument extends Document {
    void printPdf();
}

interface ExcelDocument extends Document {
    void printExcel();
}

class Word implements WordDocument {
    @Override
    public void printWord() {
        System.out.println("The document is word..");
    }

    @Override
    public void print() {
        printWord(); // Route the common factory call to your specific method
    }
}

class Pdf implements PdfDocument {
    @Override
    public void printPdf() {
        System.out.println("The document is pdf..");
    }

    @Override
    public void print() {
        printPdf(); // Route the common factory call to your specific method
    }
}

class Excel implements ExcelDocument {
    @Override
    public void printExcel() {
        System.out.println("The document is excel..");
    }

    @Override
    public void print() {
        printExcel(); // Route the common factory call to your specific method
    }
}

abstract class DocumentFactory {
    // This is the Factory Method that subclasses must implement [cite: 572, 573]
    public abstract Document createDocument();

    // An optional helper method to create and execute the behavior at once
    public void printDocument() {
        Document doc = createDocument();
        doc.print();
    }
}

class WordFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new Word();
    }
}

class PdfFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new Pdf();
    }
}

class ExcelFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new Excel();
    }
}

public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        // Create different types of documents using their respective factories [cite: 575]

        System.out.println("--- Testing Word Factory ---");
        DocumentFactory wordFactory = new WordFactory();
        wordFactory.printDocument(); // Output: The document is word..

        System.out.println("\n--- Testing PDF Factory ---");
        DocumentFactory pdfFactory = new PdfFactory();
        pdfFactory.printDocument();  // Output: The document is pdf..

        System.out.println("\n--- Testing Excel Factory ---");
        DocumentFactory excelFactory = new ExcelFactory();
        excelFactory.printDocument(); // Output: The document is excel..
    }
}
