# HS-Solr
## BlobTransformer
将Blob字段自动转为文本.

### 使用范例:

 `<entity dataSource="main" name="q" query="select * from t_z_tion where content is not null"
        deltaQuery="select id from t_z_tion where modify_time > '${dataimporter.last_index_time}'"
				transformer="cn.hongsong.solr.BlobTransformer">
        <field column="content" name="content" />
        ...
 </entity>`
