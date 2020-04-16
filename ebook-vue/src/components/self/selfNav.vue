<template>
  <div class="slef_nav">
    <div class="type no_select" v-show="!isSelectMode">
      <div class="nav nav_left"></div>
      <div class="nav nav_center">书架</div>
      <div class="nav nav_right" @click="selectMode">
        <span class="iconfont icon-edit"></span>
      </div>
    </div>
    <div class="type select" v-show="isSelectMode">
      <div class="nav nav_left" @click="deleteBook">
        <span class="iconfont icon-delete"></span>
      </div>
      <div class="nav nav_center">书架</div>
      <div class="nav nav_right" @click="selectMode">取消</div>
    </div>
  </div>
</template>
<script>
import VE from "@/util/vueEvent";
export default {
  data() {
    return {
      isSelectMode: false,
    };
  },
  methods: {
    selectMode() {
      this.isSelectMode = !this.isSelectMode;
      VE.$emit("SELECTMODE", this.isSelectMode);
    },
    deleteBook() {
      VE.$emit("DELETEBOOK", true);
    },
  },
};
</script>

<style scoped>
.type {
  height: 64px;
  display: flex;
  box-shadow: 0 1px 3px rgba(0, 25, 104, 0.3);
}
.nav {
  flex: 1;
  text-align: center;
  line-height: 64px;
}
.nav_center {
  width: 75%;
  font-size: 24px;
  font-weight: bold;
}
.select .nav_left {
  color: red;
}
</style>
