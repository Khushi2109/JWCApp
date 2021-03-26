package rishabh.example.jwcapp.ebook;

public class EbookData {

    private String PDFTitle, PDFUrl;

    public EbookData() {
        //for firebase
    }

    public EbookData(String PDFTitle, String PDFUrl) {
        this.PDFTitle = PDFTitle;
        this.PDFUrl = PDFUrl;
    }

    public String getPDFTitle() {
        return PDFTitle;
    }

    public void setPDFTitle(String PDFTitle) {
        this.PDFTitle = PDFTitle;
    }

    public String getPDFUrl() {
        return PDFUrl;
    }

    public void setPDFUrl(String PDFUrl) {
        this.PDFUrl = PDFUrl;
    }
}
