
Feature: Post Feature

  Background:
    Given User opens 'Home' page


    @R005
    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
    Scenario: R005 Check number of posts
      And Create 2 new posts via API for 'default' user and 'default' password
      When User click on 'Profile' button on 'Home' page
      Then User is redirected to Profile page
      And User sees 2 posts in Post list on Profile page