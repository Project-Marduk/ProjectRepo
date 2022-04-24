package ActiveJDBCObjecs;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.HasMany;
import org.javalite.activejdbc.annotations.Table;

@Table("folders")
@HasMany(child = UsersFolders.class, foreignKeyName = "folder_id")
public class FolderAJDBC extends Model {
    public FolderAJDBC(){}


    public Integer getFolderId(){
        return this.getInteger("id");
    }

}
