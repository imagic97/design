<template>
  <div class="about_me" style="text-align:center">
    <h2>关于</h2>
    <div>WEBOOK，沧海中一粒ePub阅读小站，愿你在书海中遨游~</div>
    <div>作者：imagic97</div>
    <div>email：315636696@qq.com</div>
    <button @click="clearCaChe()" style="background-color:#f56c6c;border:0">清空缓存</button>
  </div>
</template>
<script>
import lS from "@/util/localStorage";
export default {
  methods: {
    clearCaChe() {
      lS.clear();
    }
  }
};
</script>