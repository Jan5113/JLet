import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SafeSet {
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
}
