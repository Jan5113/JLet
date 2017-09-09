import java.util.ArrayList;

public class Set {
	public String name;
	private ArrayList<Term> terms = new ArrayList<Term>();

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

	public String termToString(int index) {
		if ((index + 1 > terms.size()) || (index < 0)) {
			return "NaN";
		}
		return "Index " + index + " | " + (terms.get(index)).toString();
	}
}
