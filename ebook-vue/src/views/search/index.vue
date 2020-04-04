<template>
  <div class="search_view">
    <div class="search_body">
      <div class="search_logo">
        <h1>WEBOOK</h1>
      </div>
      <div class="search_input">
        <span class="iconfont icon-seach"></span>
        <input
          type="text"
          placeholder="搜索:书名 / ISBN / 作者"
          class="search_inputText"
          v-model="words"
          v-on:keyup.enter="toSeach()"
        /><span class="search_input_right"></span>
      </div>
    </div>
    <div class="search_result">
      <seach-result :searchResult="result" />
    </div>
  </div>
</template>
<script>
import { searchBook } from "@/api/api";
import seachResult from "@/components/search";
import VE from "@/util/vueEvent";
export default {
  components: {
    seachResult
  },
  data() {
    return {
      words: "",
      result: []
    };
  },
  methods: {
    toSeach() {
      if (this.words == "" || this.words == " ") {
        this.result = [];
        return;
      }
      VE.$emit("isLoading", true);
      searchBook(this.words)
        .then(Response => {
          if (Response.data.code == 200) {
            this.result = Response.data.result;
          }
          VE.$emit("isLoading", false);
        })
        .catch(() => {
          VE.$emit("isLoading", false);
        });
    }
  }
};
</script>
<style scoped>
.search_body {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.search_logo h1 {
  margin-top: 36px;
  font-size: 36px;
}
.search_input {
  position: relative;
  width: 60%;
  height: 40px;
  max-width: 840px;
  margin: 24px auto 0;
  background-color: #fff;
  border-radius: 20px;
  padding: 0 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.iconfont {
  font-size: 20px;
}
.search_inputText {
  width: 100%;
  height: 100%;
  border: none;
  outline: none;
  margin: 0 16px;
}
@media (max-width: 460px) {
  .search_input {
    width: auto;
    padding-left: 20px;
    padding-right: 20px;
    margin-top: 24px;
  }
  .search_inputText {
    margin: 0 5px;
  }
}
</style>
