<template>
  <div>
    <el-row style="margin-top: 20px;">
      <el-col :span="6" :offset="1">
        <header class="header">添加电子书</header>
        <div class="upload">
          <el-upload
            class="avatar-uploader"
            ref="upload"
            drag
            action="/api/book/upload"
            name="multipartFile"
            accept="application/epub+zip"
            :headers="{ token: token }"
            :on-success="upload"
            :on-error="upload"
            :before-upload="beforeUpload"
            :limit="1"
            :file-list="fileList"
            :on-exceed="fileLimit"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击选择文件</em>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传EPUB文件，且不超过20MB</div>
          </el-upload>
        </div>
      </el-col>
    </el-row>
    <div class="info_container" v-show="showInfo">
      <book-info :bookForm="bookInfo" />
    </div>
  </div>
</template>

<script>
// import { uploadBook } from "@/api";
import { mapGetters } from "vuex";
import bookInfo from "@/components/bookInfo";

export default {
  components: {
    bookInfo
  },
  data() {
    return {
      showInfo: false,
      bookInfo: {},
      fileList: []
    };
  },
  computed: {
    ...mapGetters(["token"])
  },
  methods: {
    upload(res) {
      if (res.code == 200) {
        this.$message.success("上传成功！");
        this.bookInfo = res.result;
        this.showInfo = true;
      } else {
        this.$message.error("上传失败！");
      }
      this.$refs.upload.clearFiles();
    },
    getToken() {
      return { token: this.token };
    },
    beforeUpload(file) {
      const isRightType =
        (file.type === "application/epub+zip") |
        (file.type == "application/epub");
      const isLt20M = file.size / 1024 / 1024 < 20;
      if (!isRightType) {
        this.$message.error("上传电子书只能是 EPUB 格式!");
      }
      if (!isLt20M) {
        this.$message.error("上传电子书大小不能超过 20MB!");
      }
      return isRightType && isLt20M;
    },
    fileLimit() {
      this.$message.error("文件上传限制为单个文件");
    }
  }
};
</script>

<style lang="less">
.header {
  text-align: center;
  margin-bottom: 10px;
}
.info_container {
  padding: 16px 36px 48px 36px;
}
.upload {
  text-align: center;
  margin-top: 24px;
}
</style>
