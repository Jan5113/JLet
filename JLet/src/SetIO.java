import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SetIO {
	public static void defaultSafe (Set set_in) {
		if (new File(set_in.name + ".set").exists()) {
			if (!ConfirmWindow.display("Save Set", "The Set " + set_in.name + " already exists.\nDo you want to replace it?"))  return;
		}
		
		File f = new File(set_in.name + ".set");
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
	
	public static File[] getFiles (){
		File folder = new File(".");
		File[] matchingFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".set");
		    }
		});
//		System.out.println(folder.getAbsolutePath());
//		System.out.println(matchingFiles.length);
		
//		for (File f : matchingFiles) {
//			System.out.println(f.getName());
//		}
		
		return matchingFiles;
	}
	
	public static Set defaultOpen (File f) {

		ObjectInputStream input;
		FileInputStream fis;
		Set inputSet = new Set();
		
		try {
			fis = new FileInputStream(f);
			input = new ObjectInputStream(fis);
			
			inputSet = (Set) input.readObject();
		} catch (Exception e) {	}
		
		return inputSet;
	}
}
