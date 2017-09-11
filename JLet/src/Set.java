import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Set implements Serializable, Comparable<Set>{
	private static final long serialVersionUID = -7401207601107379118L;
	public String name;
	private ArrayList<Term> terms = new ArrayList<Term>();
	private long recentUse;
	
	public Set() {
		resetRecentUse();
	}
	
	public String getName () {
		return name;
	}

	public void addTerm(Term term_in) {
		terms.add(term_in);
	}

	public void addTerm(String[] term_in) {
		if (term_in.length >= 2) {
			terms.add(new Term(term_in[0], term_in[1]));
		}
	}

	public Term getTerm(int index) {
		return terms.get(index);
	}

	public int getSize() {
		return terms.size();
	}
	
	public String getSizeStr() {
		return Integer.toString(terms.size());
	}

	public String termToString(int index) {
		if ((index + 1 > terms.size()) || (index < 0)) {
			return "NaN";
		}
		return "Index " + index + " | " + (terms.get(index)).toString();
	}

	public String setToString() {
		String out = "Set " + name;
		for (Term t : terms) {
			out += "\n" + t.toString();
		}
		return out;
	}
	
	public boolean isEmpty() {
		return terms.isEmpty();
	}
	
	public void resetRecentUse() {
		recentUse = System.currentTimeMillis();
	}
	
	public long getRecentUse() {
		return recentUse;
	}
	
	public String getRecentUseStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");    
		Date lastOpened = new Date(getRecentUse());
		
		return sdf.format(lastOpened);
	}

	public int compareTo(Set o) {

	    if(this.getRecentUse()<o.getRecentUse())
	          return 1;
	    else if(o.getRecentUse()<this.getRecentUse())
	          return -1;
	    return 0;
	}
}
