<template>
  <div class="tabbarContainer">
    <div class="tabbar_item">
      <router-link to="/search" tag="div" class="tab-items">
        <div class="item">
          <span class="iconfont icon-seach" />
          <span class="title">搜索</span>
        </div>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/category" tag="div" class="tab-items">
        <div class="item">
          <span class="iconfont icon-Book-Shopping" />
          <span class="title">书城</span>
        </div>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/self" tag="div" class="tab-items">
        <div class="item">
          <span class="iconfont icon-self" />
          <span class="title">书架</span>
        </div>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/mine" tag="div" class="tab-items">
        <div class="item">
          <span class="iconfont icon-Personalcenter" />
          <span class="title">我</span>
        </div>
      </router-link>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  methods: {
    showSetting(value) {
      this.setMenuShow(value);
    }
  }
};
</script>
<style scoped>
.tabbarContainer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 64px;
  z-index: 99;
  box-shadow: 0 -1px 3px rgba(0, 25, 104, 0.3);
  text-align: center;
  display: flex;
  background-color: #f1f1f1;
}

.tabbar_item:hover {
  background: rgba(0, 0, 0, 0.05);
}
.tabbar_item {
  margin: 0 auto;
  flex: 1;
}

.item {
  line-height: 44px;
  -webkit-box-flex: 1;
  flex: 1;
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-direction: column;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
}
.item .iconfont {
  height: 24px;
  width: 24px;
  font-size: 24px;
}
span {
  height: 24px;
  margin: 0 auto;
}
</style>
