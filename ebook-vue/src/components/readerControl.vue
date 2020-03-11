<template>
  <div class="readerControls">
    <el-row :gutter="0">
      <el-col :span="6">
        <div class="readerControls_item readerControls_item1">
          <span class="title">书签</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="readerControls_item readerControls_item2" @click="showTheme()">
          <span class="title">主题</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="readerControls_item readerControls_item3" @click="showContent()">
          <span class="title">目录</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="readerControls_item readerControls_item4">
          <span class="title">回到主页</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  methods: {
    showContent() {
      this.setCurrentComponent("contentItem");
    },
    showTheme() {
      this.setCurrentComponent("theme");
    }
  }
};
</script>
<style scoped>
.readerControls {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 64px;
  z-index: 999;
  box-shadow: 0 -8px 21px rgba(0, 25, 104, 0.3);
  /* background-color: #fff; */
  text-align: center;
}

.readerControls_item:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
.readerControls_item {
  margin: 0 auto;
  line-height: 64px;
}

span {
  margin: 0 auto;
}
</style>
