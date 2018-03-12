package zero33.easybatch.ctrl5.search;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

// commons-io-2.4.jar import
// 
public class ElasticSearchUtils {
    private static final String ELASTICSEARCH_SEARCHTARGET_DIRECTORY = "D:\\temp\\elasticsearch";

    // 외부로부터의 ElasticSearch 검색 엔진 접근 노드 설정 및 반환
    public static Node startEmbeddedNode(){
    	// 동적 변경 금지 접근 노드 설정
        ImmutableSettings.Builder elasticSearchSettings = ImmutableSettings.settingsBuilder()
                .put("http.enabled", "false")
                .put("path.data", ELASTICSEARCH_SEARCHTARGET_DIRECTORY);

        return nodeBuilder()
                .local(true)
                .settings(elasticSearchSettings.build())
                .node();
    }

    public static void stopEmbeddedNode(Node node) {
        node.close();
        deleteElasticSearchDataDirectory();
    }

    private static void deleteElasticSearchDataDirectory() {
        try {
            FileUtils.deleteDirectory(new File(ELASTICSEARCH_SEARCHTARGET_DIRECTORY));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
