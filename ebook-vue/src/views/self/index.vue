<template>
  <div class="self_view">
    <div class="book_self" v-show="!uploadShow">
      <self-nav />
      <book-self v-on:showUpload="getIsUploadShow" />
    </div>
    <div class="upload" v-show="uploadShow">
      <upload-file v-on:hideUpload="getIsUploadShow" />
    </div>
  </div>
</template>
<script>
import bookSelf from "@/components/self/bookSelf";
import selfNav from "@/components/self/selfNav";
import uploadFile from "@/components/self/uploadFile";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    bookSelf,
    selfNav,
    uploadFile
  },
  data() {
    return {
      uploadShow: false
    };
  },
  methods: {
    getIsUploadShow(value) {
      this.uploadShow = value;
    },
    hideUpload() {
      this.uploadShow = false;
    }
  }
};
</script>
<style scoped>
.upload {
  height: 60%;
  width: 100%;
  margin-top: 145px;
}
.self_view {
  width: 100%;
  overflow: auto;
  height: 100%;
}
</style>
