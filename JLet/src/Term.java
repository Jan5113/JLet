import java.io.Serializable;

public class Term implements Serializable {
	private static final long serialVersionUID = 8600590238409882639L;
	public String term;
	public String def;
	public int correctness;
	public int timesSeen;
	public int starred;

	public Term(String term_in, String def_in) {
		term = term_in.replaceAll("\\s+", " ").trim(); // remove excess spaces
		def = def_in.replaceAll("\\s+", " ").trim();
	}

	public String toString() {
		return term + " | " + def;
	}
}
