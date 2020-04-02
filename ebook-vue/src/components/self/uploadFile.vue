<template>
  <div class="upload_file">
    <h3>上传电子书</h3>
    <div id="message" ref="message" @click="cancel()">{{ message }}</div>
    <input ref="file" type="file" @change="getFile($event)" id="file" />
    <button class="btn" @click="selectFile()">选择电子书</button>
    <button class="btn btn-upload" @click="uploadFile()">上传</button>
    <button class="btn btn-cancel" @click="hide()">取消</button>
  </div>
</template>
<script>
import { uploadBook } from "@/api/api";
export default {
  data() {
    return {
      message: "限制大小为20M",
      file: null
    };
  },
  methods: {
    selectFile() {
      this.$refs.file.click();
    },
    getFile() {
      let fileTamp = event.target.files;
      if (fileTamp.length == 0) return;
      if (fileTamp[0].type != "application/epub+zip") {
        this.message = "只支持epub格式哦";
        this.$refs.message.style.color = "red";
        return;
      } /* eslint-disable */
      if (fileTamp[0].size > 1024 * 1024 * 20) {
        this.message = "文件大小超过限制";
        this.$refs.message.style.color = "red";
        return;
      }
      this.file = fileTamp[0];
      console.log(this.file);
      this.$refs.message.style.color = "";
      this.message = fileTamp[0].name;
    },
    uploadFile() {
      if (this.file == null) {
        this.message = "请选择文件上传";
        this.$refs.message.style.color = "red";
        return;
      }
      uploadBook(this.file).then(Response => {
        if (Response.data.code == 200) {
          this.message = "上传成功";
          this.file = null;
        } else {
          this.message = Response.data.message;
        }
      });
    },
    cancel() {
      this.file = null;
      this.message = "";
    },
    hide() {
      this.cancel();
      this.$emit("hideUpload", false);
    }
  }
};
</script>
<style scoped>
.upload_file {
  width: 100%;
  z-index: 199;
  text-align: center;
}
.btn {
  width: 90px;
  height: 42px;
  margin: 0 11px;
  background-color: skyblue;
  border-radius: 5px;
  border: none;
  outline: none;
  cursor: pointer;
}
.btn-upload {
  background-color: seagreen;
}
.btn-cancel {
  width: 60px;
  height: 42px;
  background-color: red;
}

#message {
  width: 285px;
  height: 24px;
  overflow: hidden;
  border-bottom: 1px solid skyblue;
  margin: 7px auto;
}
#file {
  display: none;
}
</style>
