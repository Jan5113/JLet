import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SetIO {
	public static void defaultSafe (Set set_in, boolean ask) {
		if (new File("sets/" + set_in.name + ".set").exists() && ask) {
			if (!ConfirmWindow.display("Save Set", "The Set " + set_in.name + " already exists.\nDo you want to replace it?"))  return;
		}
		
		File f = new File("sets/" + set_in.name + ".set");
		ObjectOutputStream output;
		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(f);
			output = new ObjectOutputStream(fos);
			
			output.writeObject(set_in);

			output.close();
			fos.close();
		} catch (Exception e) {	}
	}
	
	public static Set[] getSets (){
		File folder = new File("sets");
		File[] matchingFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".set");
		    }
		});
		
		Set[] matchingSets = new Set[matchingFiles.length];
		ObjectInputStream input;
		FileInputStream fis;
		
		for (int i = 0; i < matchingFiles.length; i++) {
			Set set = new Set();
			
			try {
				fis = new FileInputStream(matchingFiles[i]);
				input = new ObjectInputStream(fis);
				
				set = (Set) input.readObject();
				fis.close();
				input.close();
			} catch (Exception e) {	}
			
			matchingSets[i] = set;
		}
		
		return matchingSets;
	}
	
	public static Set defaultOpen (File f) {

		ObjectInputStream input;
		FileInputStream fis;
		Set inputSet = new Set();
		
		try {
			fis = new FileInputStream(f);
			input = new ObjectInputStream(fis);
			
			inputSet = (Set) input.readObject();
			fis.close();
			input.close();
		} catch (Exception e) {	}
		
		return inputSet;
	}
}
