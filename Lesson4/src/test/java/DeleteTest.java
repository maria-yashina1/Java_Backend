import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Lesson4.PostImageResponse;
import Lesson4.PostImageResponseBase;

import static io.restassured.RestAssured.given;
import static Lesson4.Endpoints.*;

class DeleteTest extends BaseTest {
    String uploadedImageId;

    @Test
    @DisplayName("Загрузка картинки")
    void uploadFileBase64Test() {
        uploadedImageId = given(requestSpecUploadImageBase64)
                .post(UPLOAD_BASE64).prettyPeek().then()
                .spec(uploadFileImageBase64ResponseSpecification)
                .extract().body().as(PostImageResponse.class)
                .getData().getDeletehash();
    }

    @AfterEach
    @DisplayName("Удаление картинки")
    void tearDown() {
        given(requestSpecDeleteImageBase64)
                .delete(GET_DELETE, username, uploadedImageId).prettyPeek().then()
                .spec(deleteFileImageBase64ResponseSpecification)
                .extract().body().as(PostImageResponseBase.class);
    }
}