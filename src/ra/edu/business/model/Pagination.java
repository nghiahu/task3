package ra.edu.business.model;

public class Pagination {
    private int pagesize;
    private int currentpage;
    private int totalpages;

    public Pagination() {
    }

    public Pagination(int pagesize, int currentpage, int totalpages) {
        this.pagesize = pagesize;
        this.currentpage = currentpage;
        this.totalpages = totalpages;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int total) {
        this.totalpages = total / this.pagesize;
        if(total % this.pagesize != 0) {
            totalpages++;
        }
    }
}
